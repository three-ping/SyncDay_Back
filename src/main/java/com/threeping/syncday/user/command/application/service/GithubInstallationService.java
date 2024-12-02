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

    public Map<String, Object> validateInstallation(Long installationId) {
        try {
            GHAppInstallationToken installationToken = getInstallationToken(installationId);

            GitHub github = new GitHubBuilder()
                    .withOAuthToken(installationToken.getToken())
                    .build();

            // Get installation information
            Map<String, Object> info = new HashMap<>();
            info.put("token", installationToken.getToken());
            info.put("permissions", installationToken.getPermissions());

            // Try to get rate limit info as a basic API test
            GHRateLimit rateLimit = github.getRateLimit();
            info.put("rateLimit", rateLimit.getLimit());
            info.put("remaining", rateLimit.getRemaining());

            return info;

        } catch (Exception e) {
            log.error("Failed to validate installation ID: {}", installationId, e);
            throw new RuntimeException("Failed to validate GitHub installation: " + e.getMessage());
        }
    }

    public GHAppInstallationToken getInstallationToken(Long installationId) {
        try {
            String jwtToken = githubJwtUtils.createJWT();

            GitHub gitHubApp = new GitHubBuilder()
                    .withJwtToken(jwtToken)
                    .build();

            GHAppInstallation installation = gitHubApp.getApp().getInstallationById(installationId);
            return installation.createToken().create();

        } catch (Exception e) {
            log.error("Failed to get installation token for ID: {}", installationId, e);
            throw new RuntimeException("Failed to get GitHub installation token", e);
        }
    }
}