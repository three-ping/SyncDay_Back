package com.threeping.syncday.config;

import com.threeping.syncday.user.security.GithubJwtUtils;
import io.jsonwebtoken.SignatureAlgorithm;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class GithubAppConfig {

   private final GithubJwtUtils githubJwtUtils;

   @Autowired
   public GithubAppConfig(GithubJwtUtils githubJwtUtils) {
       this.githubJwtUtils = githubJwtUtils;
   }

    @Bean
    public GitHubBuilder githubBuilder(){
        return new GitHubBuilder();
    }
    @Bean
    public GitHub gitHub() throws Exception {
        return githubBuilder()
                .withJwtToken(githubJwtUtils.createJwt())
                .build();
    }
}
