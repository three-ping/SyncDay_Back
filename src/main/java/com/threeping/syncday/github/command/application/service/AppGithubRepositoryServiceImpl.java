package com.threeping.syncday.github.command.application.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.threeping.syncday.common.exception.CommonException;
import com.threeping.syncday.common.exception.ErrorCode;
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
    public Boolean saveInstallationRepositories(Long installationId, GHAppInstallation installation){
        try {

        List<GHRepository> repositories = getInstallationRepositoriesViaRest(installation);
            log.info("repositories: {}", repositories);
        }catch (Exception e){
            throw new CommonException(ErrorCode.GITHUB_INSTLLATION_REPOSITORY_SAVE_FAILURE);
        }

        return Boolean.FALSE;
    }

    private List<GHRepository> getInstallationRepositoriesViaRest(GHAppInstallation installation) throws IOException {
        try {
            GHAppInstallationToken token = installation.createToken().create();

            WebClient webClient = WebClient.builder()
                    .baseUrl("https://api.github.com")
                    .defaultHeader(HttpHeaders.ACCEPT, "application/vnd.github.v3+json")
                    .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token.getToken())
                    .build();

            List<GHRepository> allRepositories = new ArrayList<>();
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
                        .block();  // blocking for simplicity, could be made reactive if needed

                JsonNode repositories = responseNode.get("repositories");

                if (repositories.size() == 0) {
                    break;  // No more repositories to fetch
                }

                // Connect with installation token to get repository objects
                GitHub gitHub = new GitHubBuilder()
                        .withAppInstallationToken(token.getToken())
                        .build();

                for (JsonNode repoNode : repositories) {
                    String fullName = repoNode.get("full_name").asText();
                    GHRepository repository = gitHub.getRepository(fullName);
                    allRepositories.add(repository);
                }

                // Check if we've received less than perPage items
                if (repositories.size() < perPage) {
                    break;
                }

                page++;
            }

            return allRepositories;

        } catch (WebClientResponseException e) {
            log.error("GitHub API error for installation {}: {} - {}",
                    installation.getId(), e.getRawStatusCode(), e.getResponseBodyAsString());
            throw new IOException("Failed to fetch installation repositories", e);
        } catch (IOException e) {
            log.error("Failed to fetch repositories for installation {}: {}",
                    installation.getId(), e.getMessage());
            throw new IOException("Failed to fetch installation repositories", e);
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }

}
