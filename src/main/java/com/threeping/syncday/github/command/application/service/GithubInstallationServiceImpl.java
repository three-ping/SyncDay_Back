package com.threeping.syncday.github.command.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.threeping.syncday.github.command.aggregate.entity.GithubInstallationEntity;
import com.threeping.syncday.github.command.aggregate.vo.InstallationAuthRequest;
import com.threeping.syncday.github.command.aggregate.vo.SecretData;
import com.threeping.syncday.github.command.domain.GithubInstallationRepository;
import com.threeping.syncday.github.command.domain.model.GithubSecretData;
import com.threeping.syncday.github.command.infrastructure.aws.SecretsManagerService;
import com.threeping.syncday.github.command.infrastructure.aws.SecretsService;
import com.threeping.syncday.github.command.infrastructure.github.GithubAppClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class GithubInstallationServiceImpl implements GithubInstallationService {

    private final SecretsManagerService secretsManager;
    private static final String SECRET_PREFIX = "github/installation/";

    public void storeInstallationSecret(Long installationId, String accessToken) {
        String secretName = SECRET_PREFIX + installationId;

        GithubSecretData secretData = GithubSecretData.builder()
                .installationId(installationId)
                .accessToken(accessToken)
                .tokenExpiresAt(LocalDateTime.now().plusHours(1))
                .createdAt(LocalDateTime.now())
                .build();

        secretsManager.createSecret(secretName, secretData);
    }

    public GithubSecretData getInstallationSecret(Long installationId) {
        String secretName = SECRET_PREFIX + installationId;
        return secretsManager.getSecret(secretName, GithubSecretData.class);
    }

    public void rotateInstallationToken(Long installationId, String newToken) {
        String secretName = SECRET_PREFIX + installationId;
        GithubSecretData currentSecret = getInstallationSecret(installationId);

        GithubSecretData newSecretData = GithubSecretData.builder()
                .installationId(installationId)
                .accessToken(newToken)
                .tokenExpiresAt(LocalDateTime.now().plusHours(1))
                .webhookSecret(currentSecret.getWebhookSecret())
                .additionalData(currentSecret.getAdditionalData())
                .createdAt(currentSecret.getCreatedAt())
                .build();

        secretsManager.rotateSecret(secretName, newSecretData);
    }

    @Override
    public Boolean processInstallationAuth(InstallationAuthRequest githubInstallationRequest) throws JsonProcessingException {
String
    }
}
