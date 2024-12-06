package com.threeping.syncday.github.command.infrastructure.github;


import com.threeping.syncday.common.exception.CommonException;
import com.threeping.syncday.common.exception.ErrorCode;
import com.threeping.syncday.github.config.GitHubFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kohsuke.github.GHAppInstallation;
import org.kohsuke.github.GHPerson;
import org.kohsuke.github.GitHub;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class GithubAppClient {

    private final GitHubFactory gitHubFactory;

    public GHAppInstallation getGithubInstallation(Long installationId) {
        try {
            GitHub github = gitHubFactory.createGithub();
            return github.getApp().getInstallationById(installationId);

        } catch (IOException e) {
            throw new CommonException(ErrorCode.GITHUB_APP_INSTALLATION_CLIENT_ERROR);
        }
    }
}
