package com.threeping.syncday.github.command.infrastructure.aws;


import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.threeping.syncday.common.exception.CommonException;
import com.threeping.syncday.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@Slf4j
public class SecretsManagerService {
    private final AWSSecretsManager secretsManager;
    private final ObjectMapper objectMapper;

    public void createSecret(String secretName, Object secretValue) {
        try {
            String secretString = objectMapper.writeValueAsString(secretValue);
            CreateSecretRequest request = new CreateSecretRequest()
                    .withName(secretName)
                    .withSecretString(secretString)
                    .withDescription("GitHub Installation Secret")
                    .withTags(Collections.singletonList(
                            new Tag()
                                    .withKey("Application")
                                    .withValue("GitHub-Integration")
                    ));

            try {
                secretsManager.createSecret(request);
                log.info("Created new secret: {}", secretName);
            } catch (ResourceExistsException e) {
                UpdateSecretRequest updateRequest = new UpdateSecretRequest()
                        .withSecretId(secretName)
                        .withSecretString(secretString);
                secretsManager.updateSecret(updateRequest);
                log.info("Updated existing secret: {}", secretName);
            }
        } catch (Exception e) {
            log.error("Failed to store secret: {}", secretName, e);
            throw new CommonException(ErrorCode.AWS_SECRET_OPERATION_EXCEPTION);
        }
    }

    public <T> T getSecret(String secretName, Class<T> valueType) {
        try {
            GetSecretValueRequest request = new GetSecretValueRequest()
                    .withSecretId(secretName)
                    .withVersionStage("AWSCURRENT");

            GetSecretValueResult result = secretsManager.getSecretValue(request);
            String secret = result.getSecretString();

            return objectMapper.readValue(secret, valueType);
        } catch (ResourceNotFoundException e) {
            log.error("Secret not found: {}", secretName);
            throw new CommonException(ErrorCode.AWS_SECRET_OPERATION_EXCEPTION);
        } catch (Exception e) {
            log.error("Failed to retrieve secret: {}", secretName, e);
            throw new CommonException(ErrorCode.AWS_SECRET_OPERATION_EXCEPTION);
        }
    }

    public void deleteSecret(String secretName) {
        try {
            DeleteSecretRequest request = new DeleteSecretRequest()
                    .withSecretId(secretName)
                    .withForceDeleteWithoutRecovery(false);  // 30일 복구 기간 설정

            secretsManager.deleteSecret(request);
            log.info("Deleted secret: {}", secretName);
        } catch (Exception e) {
            log.error("Failed to delete secret: {}", secretName, e);
            throw new CommonException(ErrorCode.AWS_SECRET_OPERATION_EXCEPTION);
        }
    }

    public void rotateSecret(String secretName, Object newSecretValue) {
        try {
            String secretString = objectMapper.writeValueAsString(newSecretValue);
            PutSecretValueRequest request = new PutSecretValueRequest()
                    .withSecretId(secretName)
                    .withSecretString(secretString);

            secretsManager.putSecretValue(request);
            log.info("Rotated secret: {}", secretName);
        } catch (Exception e) {
            log.error("Failed to rotate secret: {}", secretName, e);
            throw new CommonException(ErrorCode.AWS_SECRET_OPERATION_EXCEPTION);
        }
    }
}
