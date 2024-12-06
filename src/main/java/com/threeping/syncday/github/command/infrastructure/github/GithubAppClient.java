package com.threeping.syncday.github.command.infrastructure.github;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.threeping.syncday.github.command.aggregate.entity.GithubRepositoryEntity;
import com.threeping.syncday.github.config.GithubAppConfig;
import com.threeping.syncday.github.security.util.GithubJwtUtils;
import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kohsuke.github.GHAppInstallationToken;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class GithubAppClient {

    private final GithubAppConfig appConfig;
    private final GithubJwtUtils jwtUtils;
    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper objectMapper;
    private static final String TOKEN_CACHE_PREFIX = "github:installation:token:";
    private static final String REPOSITORY_CACHE_PREFIX = "github:installation:repos:";
    private static final Duration TOKEN_CACHE_TTL = Duration.ofMinutes(50); // GitHub token expires in 1 hour
    private static final Duration REPOSITORY_CACHE_TTL = Duration.ofMinutes(30);

    private GitHub createGitHubClient(String installationToken) throws IOException, java.io.IOException {
        return new GitHubBuilder()
                .withOAuthToken(installationToken)
                .build();
    }

    public String getInstallationToken(Long installationId) {
        String cachedToken = redisTemplate.opsForValue().get(TOKEN_CACHE_PREFIX + installationId);
        if (cachedToken != null) {
            return cachedToken;
        }

        try {
            String jwt = jwtUtils.createJwt();
            GitHubBuilder builder = new GitHubBuilder();

            // Connect as GitHub App
            GitHub gitHubApp = builder.withJwtToken(jwt).build();

            // Create new installation token
            GHAppInstallationToken token = gitHubApp
                    .getApp()
                    .getInstallationById(installationId)
                    .createToken()
                    .create();

            // Cache the token
            redisTemplate.opsForValue().set(
                    TOKEN_CACHE_PREFIX + installationId,
                    token.getToken(),
                    TOKEN_CACHE_TTL
            );

            return token.getToken();
        } catch (IOException e) {
            log.error("Failed to create installation token for id: {}", installationId, e);
            throw new RuntimeException("Failed to create installation token", e);
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<GithubRepositoryEntity> getInstallationRepositories(Long installationId) {
        String cacheKey = REPOSITORY_CACHE_PREFIX + installationId;
        String cachedRepos = redisTemplate.opsForValue().get(cacheKey);

        if (cachedRepos != null) {
            try {
                return objectMapper.readValue(cachedRepos,
                        new TypeReference<List<GithubRepositoryEntity>>() {
                        });
            } catch (JsonProcessingException e) {
                log.error("Failed to deserialize cached repositories", e);
            }
        }

        try {
            String token = getInstallationToken(installationId);
            GitHub gitHub = createGitHubClient(token);

            List<GithubRepositoryEntity> repositories = gitHub
                    .getApp()
                    .getInstallationById(installationId)
                    .listRepositories()
                    .toList()
                    .stream()
                    .map(this::convertToEntity)
                    .collect(Collectors.toList());

            // Cache repositories
            try {
                redisTemplate.opsForValue().set(
                        cacheKey,
                        objectMapper.writeValueAsString(repositories),
                        REPOSITORY_CACHE_TTL
                );
            } catch (JsonProcessingException e) {
                log.error("Failed to cache repositories", e);
            }

            return repositories;
        } catch (IOException | java.io.IOException e) {
            log.error("Failed to fetch installation repositories for id: {}", installationId, e);
            throw new RuntimeException("Failed to fetch repositories", e);
        }
    }


    private GithubRepositoryEntity convertToEntity(GHRepository repository) {
        try {
            return GithubRepositoryEntity.builder()
                    .repoId(repository.getId())
                    .repoName(repository.getName())
                    .repoFullName(repository.getFullName())
                    .isPrivate(repository.isPrivate())
                    .description(repository.getDescription())
                    .htmlUrl(repository.getHttpTransportUrl())
                    .defaultBranch(repository.getDefaultBranch())
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
        } catch (IOException e) {
            log.error("Failed to convert repository: {}", repository.getFullName(), e);
            throw new RuntimeException("Failed to convert repository", e);
        }
    }
}
