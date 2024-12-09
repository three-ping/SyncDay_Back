package com.threeping.syncday.github.config;

import com.threeping.syncday.github.security.util.GithubJwtUtils;
import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class GitHubFactory {

    private final GithubJwtUtils githubJwtUtils;

    public GitHub createGithub() throws IOException, java.io.IOException {
        String jwt = githubJwtUtils.createJwt();
        return new GitHubBuilder()
                .withJwtToken(jwt)
                .build();
    }

    public GitHub createGitHubWithInstallationToken(String installationToken) throws java.io.IOException {
        log.debug("installationToken: {}", installationToken);
         return new GitHubBuilder().withAppInstallationToken(installationToken).build();
    }

}
