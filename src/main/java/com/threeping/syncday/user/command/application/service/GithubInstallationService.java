package com.threeping.syncday.user.command.application.service;

import com.threeping.syncday.user.security.GithubJwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.kohsuke.github.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;

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
            String jwtToken = githubJwtUtils.createJWT();
            log.debug("Generated JWT token for installation ID: {}", installationId);

            GitHub gitHubApp = new GitHubBuilder()
                    .withJwtToken(jwtToken)
                    .build();

            GHAppInstallation installation = gitHubApp.getApp().getInstallationById(installationId);
            if (installation == null) {
                log.error("No installation found for ID: {}", installationId);
                throw new RuntimeException("Installation not found");
            }

            GHAppInstallationToken token = installation.createToken().create();
            log.info("Successfully created installation token for ID: {}", installationId);
            log.info("token: {}", token.getPermissions());

            return token;

        } catch (Exception e) {
            log.error("Failed to get installation token for ID: {}", installationId, e);
            throw new RuntimeException("Failed to get GitHub installation token", e);
        }
    }
    public GitHub getGitHubClient(Long installationId) {
        try{

        }
    }
}


