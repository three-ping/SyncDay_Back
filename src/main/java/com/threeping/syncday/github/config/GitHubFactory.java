package com.threeping.syncday.github.config;

import com.threeping.syncday.github.security.util.GithubJwtUtils;
import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GitHubFactory {

    private final GithubJwtUtils githubJwtUtils;

    public GitHub createGithub() throws IOException, java.io.IOException {
        String jwt = githubJwtUtils.createJwt();
        return new GitHubBuilder()
                .withJwtToken(jwt)
                .build();
    }

}
