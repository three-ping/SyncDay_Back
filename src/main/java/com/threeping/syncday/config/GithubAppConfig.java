package com.threeping.syncday.config;

import org.kohsuke.github.GHAppInstallationToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GithubAppConfig {

    @Value("${github.app.id}")
    private String appId;

    @Value("${github.app.private-key}")
    private String privateKey;

    @Bean
    public GHAppInstallationToken installationToken(){
        return new GHAppInstallationToken();
    }
}
