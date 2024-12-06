package com.threeping.syncday.github.command.infrastructure.github;

import com.nimbusds.oauth2.sdk.TokenResponse;
import com.threeping.syncday.github.command.infrastructure.aws.SecretsManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class GithubAppClient {
    private final WebClient webClient;
    private final SecretsManagerService secretsService;

    @Value("${github.app.client-id}")
    private String clientId;

    public String exchangeCodeForToken(String code) {
        String clientSecret = secretsService.getInstallationSecret();

        TokenResponse response = webClient.post()
                .uri("https://github.com/login/oauth/access_token")
                .header("Accept", "application/json")
                .body(BodyInserters.fromFormData("client_id", clientId)
                        .with("client_secret", clientSecret)
                        .with("code", code))
                .retrieve()
                .bodyToMono(TokenResponse.class)
                .block();

        return response.getAccessToken();
    }
}