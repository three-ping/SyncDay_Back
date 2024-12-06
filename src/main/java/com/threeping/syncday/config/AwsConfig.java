package com.threeping.syncday.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsConfig {
    @Value("${aws.region.static}")
    private String region;

    @Bean
    public AWSSecretsManager awsSecretsManager(AWSCredentialsProvider awsCredentialsProvider) {
        return AWSSecretsManagerClientBuilder.standard()
                .withRegion(region)
                .withCredentials(awsCredentialsProvider)
                .build();
    }

    @Bean
    public AWSCredentialsProvider awsCredentialsProvider() {
        return DefaultAWSCredentialsProviderChain.getInstance();
    }
}
