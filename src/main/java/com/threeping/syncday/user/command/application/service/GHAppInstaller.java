package com.threeping.syncday.user.command.application.service;

import com.threeping.syncday.user.security.GithubJwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.kohsuke.github.GHAppInstallation;
import org.kohsuke.github.GHAppInstallationToken;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GHAppInstaller {

    private final GithubJwtUtils githubJwtUtils;

    @Autowired
    public GHAppInstaller(GithubJwtUtils githubJwtUtils) {
        this.githubJwtUtils = githubJwtUtils;
    }

    public GHAppInstallationToken createGithubInstallationToken(Long installationId) throws Exception {
        try {
            String jwtToken = githubJwtUtils.createJwt();
            GitHub githubApp = new GitHubBuilder()
                    .withJwtToken(jwtToken)
                    .build();
            GHAppInstallation installation = githubApp.getApp().getInstallationById(installationId);

            return installation.createToken().create();
        } catch (Exception e) {
            log.error("Failed to get installation token for ID: {}", installationId, e);
            throw new RuntimeException("Failed to get GitHub installation token", e);

        }

    }





}
