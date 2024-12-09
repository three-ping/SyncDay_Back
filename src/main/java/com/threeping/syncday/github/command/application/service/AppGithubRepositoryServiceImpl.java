package com.threeping.syncday.github.command.application.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.threeping.syncday.common.exception.CommonException;
import com.threeping.syncday.common.exception.ErrorCode;
import com.threeping.syncday.github.command.aggregate.entity.GithubRepositoryEntity;
import com.threeping.syncday.github.command.domain.GithubRepoRepository;
import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kohsuke.github.*;
import org.springframework.stereotype.Service;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AppGithubRepositoryServiceImpl implements AppGithubRepositoryService {

    private final GithubRepoRepository githubRepoRepository;

    @Override
    public Boolean saveInstallationRepositories(Long userId, Long installationId, GHAppInstallation installation) {
        try {

            List<GithubRepositoryEntity> repositories = getInstallationRepositoriesViaRest(userId, installation);
            log.info("repositories: {}", repositories);
        } catch (Exception e) {
            throw new CommonException(ErrorCode.GITHUB_INSTLLATION_REPOSITORY_SAVE_FAILURE);
        }

        return Boolean.FALSE;
    }

    private List<GithubRepositoryEntity> getInstallationRepositoriesViaRest(Long userId, GHAppInstallation installation) throws IOException {
        try {
            GHAppInstallationToken token = installation.createToken().create();
            Long installationId = installation.getId();
            WebClient webClient = WebClient.builder()
                    .baseUrl("https://api.github.com")
                    .defaultHeader(HttpHeaders.ACCEPT, "application/vnd.github.v3+json")
                    .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token.getToken())
                    .build();

            List<GithubRepositoryEntity> allRepositories = new ArrayList<>();
            int page = 1;
            final int perPage = 100;  // GitHub's max items per page

            while (true) {
                int finalPage = page;
                JsonNode responseNode = webClient.get()
                        .uri(uriBuilder -> uriBuilder
                                .path("/installation/repositories")
                                .queryParam("page", finalPage)
                                .queryParam("per_page", perPage)
                                .build())
                        .retrieve()
                        .bodyToMono(JsonNode.class)
                        .block();

                JsonNode repositories = responseNode.get("repositories");

                if (repositories.size() == 0) {
                    break;  // No more repositories to fetch
                }

                for (JsonNode repo : repositories) {
                    GithubRepositoryEntity entity = GithubRepositoryEntity.builder()
                            .installationId(installationId)
                            .userId(userId)
                            .repoId(repo.get("id").asLong())
                            .repoName(repo.get("name").asText())
                            .repoFullName(repo.get("full_name").asText())
                            .repoUrl(repo.get("url").asText())
                            .htmlUrl(repo.get("html_url").asText())
                            .defaultBranch(repo.get("default_branch").asText())
                            .isPrivate(repo.get("private").asBoolean())
                            .isActive(true)
                            .build();

                    allRepositories.add(entity);
                }

                // Check if we've received less than perPage items
                if (repositories.size() < perPage) {
                    break;
                }

                page++;
            }

            // Batch save all repositories
            if (!allRepositories.isEmpty()) {
                List<GithubRepositoryEntity> repos = githubRepoRepository.saveAll(allRepositories);
                return repos;
            }

        } catch (WebClientResponseException e) {
            log.error("GitHub API error for installation {}: {} - {}",
                    installation.getId(), e.getRawStatusCode(), e.getResponseBodyAsString());
            throw new IOException("Failed to fetch installation repositories", e);
        } catch (IOException | java.io.IOException e) {
            log.error("Failed to fetch repositories for installation {}: {}",
                    installation.getId(), e.getMessage());
            throw new IOException("Failed to fetch installation repositories", e);
        }


        return null;
    }
}
