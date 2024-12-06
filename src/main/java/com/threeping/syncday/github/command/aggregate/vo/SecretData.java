package com.threeping.syncday.github.command.aggregate.vo;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class SecretData {
private Long installationId;
private String accessToken;
private LocalDateTime createdAt;

    @Builder
    public SecretData(Long installationId, String accessToken) {
        this.installationId = installationId;
        this.accessToken = accessToken;
        this.createdAt = LocalDateTime.now();
    }
}
