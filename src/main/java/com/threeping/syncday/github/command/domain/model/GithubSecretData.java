package com.threeping.syncday.github.command.domain.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.threeping.syncday.github.command.aggregate.enums.AccountType;
import com.threeping.syncday.github.command.aggregate.enums.InstallationStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GithubSecretData {
    private Long installationId;
    private Long accountId;
    private String webhookSecret;
    private AccountType accountType;
    private String accountName;

    @Builder.Default
    private InstallationStatus status = InstallationStatus.ACTIVE;
}