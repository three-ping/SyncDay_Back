package com.threeping.syncday.user.command.application.service;

import com.threeping.syncday.user.security.GithubJwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.kohsuke.github.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GithubInstallationService {

    private final GithubJwtUtils githubJwtUtils;

    @Autowired
    public GithubInstallationService(GithubJwtUtils githubJwtUtils) {
        this.githubJwtUtils = githubJwtUtils;
    }

    public GHAppInstallationToken getInstallationToken(Long installationId) {
        try {
            // Generate JWT token for GitHub App authentication
            String jwtToken = githubJwtUtils.createJWT();
            log.debug("Generated JWT token for installation ID: {}", installationId);

            // Create GitHub instance with JWT authentication
            GitHub gitHubApp = new GitHubBuilder()
                    .withJwtToken(jwtToken)
                    .build();

            // Get the installation
            GHAppInstallation installation = gitHubApp.getApp().getInstallationById(installationId);
            if (installation == null) {
                log.error("No installation found for ID: {}", installationId);
                throw new RuntimeException("GitHub App installation not found");
            }

            // Create installation token
            GHAppInstallationToken token = installation.createToken().create();
            log.info("Successfully created installation token for ID: {}", installationId);
            log.debug("Token permissions: {}", token.getPermissions());

            return token;

        } catch (Exception e) {
            log.error("Failed to get installation token for ID: {}", installationId, e);
            throw new RuntimeException("Failed to get GitHub installation token", e);
        }
    }

    public GitHub getGithubClient(Long installationId) {
        try {
            GHAppInstallationToken token = getInstallationToken(installationId);
            return new GitHubBuilder()
                    .withAppInstallationToken(token.getToken())
                    .build();
        } catch (Exception e) {
            log.error("Failed to create GitHub client for installation ID: {}", installationId, e);
            throw new RuntimeException("Failed to create GitHub client", e);
        }
    }

    public Map<String, String> validateInstallation(Long installationId) {
        try {
            GitHub github = getGithubClient(installationId);
            GHApp app = github.getApp();

            Map<String, String> info = new HashMap<>();
            info.put("appName", app.getName());
            info.put("appOwner", app.getOwner().getLogin());
            info.put("appUrl", app.getHtmlUrl().toString());

            return info;
        } catch (Exception e) {
            log.error("Failed to validate installation ID: {}", installationId, e);
            throw new RuntimeException("Failed to validate GitHub installation", e);
        }
    }

    public List<GHRepository> getAccessibleRepositories(Long installationId) {
        try {
            GitHub github = getGithubClient(installationId);
            return github.getMyself().getAllRepositories().values()
                    .stream()
//                    .filter(repo -> repo.getPermissions().hasPullAccess())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Failed to get accessible repositories for installation ID: {}", installationId, e);
            throw new RuntimeException("Failed to get accessible repositories", e);
        }
    }

    public String getInstallationName(Long installationId) {
        try {
            GitHub gitHubApp = new GitHubBuilder()
                    .withJwtToken(githubJwtUtils.createJWT())
                    .build();

            GHAppInstallation installation = gitHubApp.getApp().getInstallationById(installationId);
            return installation.getAccount().getLogin();
        } catch (Exception e) {
            log.error("Failed to get installation name for ID: {}", installationId, e);
            throw new RuntimeException("Failed to get installation name", e);
        }
    }
}