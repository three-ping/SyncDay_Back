package com.threeping.syncday.github.query.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.threeping.syncday.common.exception.CommonException;
import com.threeping.syncday.common.exception.ErrorCode;
import com.threeping.syncday.github.command.aggregate.entity.GithubRepositoryEntity;
import com.threeping.syncday.github.infrastructure.github.GithubAppClient;
import com.threeping.syncday.github.query.aggregate.dto.GithubMilestoneDTO;
import com.threeping.syncday.github.query.aggregate.dto.GithubRepositoryDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kohsuke.github.GHAppInstallation;
import org.kohsuke.github.GHAppInstallationToken;
import org.kohsuke.github.GHMilestone;
import org.kohsuke.github.GitHub;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static jakarta.xml.bind.DatatypeConverter.parseDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class GithubMilestoneServiceImpl implements GithubMilestoneService {
    private final RestTemplate restTemplate;
    private final GithubRepositoryService githubRepositoryService;
    private final GithubAppClient githubAppClient;

    @Override
    public List<GithubMilestoneDTO> getMilestones(Long repoId) {
        try {
            GithubRepositoryDTO repo = githubRepositoryService.getById(repoId);
            log.info("Fetching milestones for repository: {}", repo.getRepoFullName());

            GHAppInstallationToken token = githubAppClient.getInstallationToken(repo.getInstallationId());

            WebClient webClient = WebClient.builder()
                    .baseUrl(repo.getRepoUrl())
                    .defaultHeader(HttpHeaders.ACCEPT, "application/vnd.github.v3+json")
                    .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token.getToken())
                    .build();

            List<GithubMilestoneDTO> allMilestones = new ArrayList<>();
            int page = 1;
            final int perPage = 100;  // GitHub's max items per page

            while (true) {
                int finalPage = page;
                JsonNode responseNode = webClient.get()
                        .uri(uriBuilder -> uriBuilder
                                .path("/milestones")
                                .queryParam("state", "open")  // Can be modified to include 'closed' if needed
                                .queryParam("page", finalPage)
                                .queryParam("per_page", perPage)
                                .build())
                        .retrieve()
                        .bodyToMono(JsonNode.class)
                        .block();

                if (responseNode == null || responseNode.size() == 0) {
                    break;  // No more milestones to fetch
                }

                for (JsonNode milestone : responseNode) {
                    Calendar createdAt = new ObjectMapper()
                            .convertValue(milestone.get("created_at"), Calendar.class);
                    Calendar updatedAt = new ObjectMapper()
                            .convertValue(milestone.get("updated_at"), Calendar.class);
                    Calendar dueOn = milestone.get("due_on") != null && !milestone.get("due_on").isNull()
                            ? new ObjectMapper().convertValue(milestone.get("due_on"), Calendar.class)
                            : null;

                    GithubMilestoneDTO dto = GithubMilestoneDTO.builder()
                            .id(milestone.get("id").asLong())
                            .number(milestone.get("number").asLong())
                            .title(milestone.get("title").asText())
                            .description(milestone.get("description").asText(""))
                            .state(milestone.get("state").asText())
                            .openIssues(milestone.get("open_issues").asInt())
                            .closedIssues(milestone.get("closed_issues").asInt())
                            .createdAt(createdAt != null ?
                                    LocalDateTime.ofInstant(createdAt.toInstant(), ZoneId.systemDefault()) : null)
                            .updatedAt(updatedAt != null ?
                                    LocalDateTime.ofInstant(updatedAt.toInstant(), ZoneId.systemDefault()) : null)
                            .dueOn(dueOn != null ?
                                    LocalDateTime.ofInstant(dueOn.toInstant(), ZoneId.systemDefault()) : null)
                            .htmlUrl(milestone.get("html_url").asText())
                            .repoId(repoId)
                            .build();

                    allMilestones.add(dto);
                }

                // Check if we've received less than perPage items
                if (responseNode.size() < perPage) {
                    break;
                }

                page++;
            }

            log.info("Successfully fetched {} milestones for repository: {}",
                    allMilestones.size(), repo.getRepoFullName());
            return allMilestones;

        } catch (WebClientResponseException e) {
            log.error("GitHub API error while fetching milestones for repo {}: {} - {}",
                    repoId, e.getRawStatusCode(), e.getResponseBodyAsString());
            throw new CommonException(ErrorCode.MILESTONE_API_EXCEPTION);
        } catch (Exception e) {
            log.error("Error fetching milestones for repo {}: {}", repoId, e.getMessage());
            throw new CommonException(ErrorCode.MILESTONE_API_EXCEPTION);
        }
    }
}
