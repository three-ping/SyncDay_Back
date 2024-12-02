package com.threeping.syncday.user.command.application.service;

import com.threeping.syncday.user.security.GithubJwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.kohsuke.github.GHAppInstallation;
import org.kohsuke.github.GHAppInstallationToken;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
@Slf4j
@Service
public class GithubInstallationService {

    private final GithubJwtUtils githubJwtUtils;

    @Autowired
    public GithubInstallationService(GithubJwtUtils githubJwtUtils) {
        this.githubJwtUtils = githubJwtUtils;
    }

    public GHAppInstallationToken getInstallationToken(Long installationId) throws Exception {
        // First, authenticate as GitHub App using JWT

        log.info("installationId: {}", installationId);
        String jwtToken = githubJwtUtils.createJWT();
        log.info("jwtToken: {}", jwtToken);
        GitHub gitHubApp = new GitHubBuilder().withJwtToken(jwtToken).build();
        log.info("gitHubApp: {}", gitHubApp);
        // Get an installation token
        GHAppInstallation appInstallation = gitHubApp.getApp().getInstallationById(installationId);
        log.info("appInstallation: {}", appInstallation);
        return appInstallation.createToken().create();
    }

}
