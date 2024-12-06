package com.threeping.syncday.github.command.infrastructure.aws;

import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.threeping.syncday.common.exception.CommonException;
import com.threeping.syncday.common.exception.ErrorCode;
import com.threeping.syncday.github.command.aggregate.vo.SecretData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecretsService {
    private final AWSSecretsManager secretsManager;
    private final ObjectMapper objectMapper;

    public void storeSecret(String secretName, SecretData secretData) throws JsonProcessingException {
        try {
            String secretString = objectMapper.writeValueAsString(secretData);

            CreateSecretRequest request = new CreateSecretRequest()
                    .withName(secretName)
                    .withSecretString(secretString);

            secretsManager.createSecret(request);
        } catch (ResourceExistsException e) {
            UpdateSecretRequest request = new UpdateSecretRequest()
                    .withSecretId(secretName)
                    .withSecretString(objectMapper.writeValueAsString(secretData));

            secretsManager.updateSecret(request);
        } catch (JsonProcessingException e) {
            throw new CommonException(ErrorCode.AWS_SECRET_OPERATION_EXCEPTION);
        }
    }

    public SecretData getInstallationSecret(Long installationId) {
        try {
            String secretName = String.format("github/installation/%s", installationId);
            GetSecretValueRequest request = new GetSecretValueRequest()
                    .withSecretId(secretName);

            GetSecretValueResult result = secretsManager.getSecretValue(request);
            return objectMapper.readValue(result.getSecretString(), SecretData.class);
        } catch (Exception e) {
            throw new CommonException(ErrorCode.AWS_SECRET_OPERATION_EXCEPTION);
        }
    }
}
