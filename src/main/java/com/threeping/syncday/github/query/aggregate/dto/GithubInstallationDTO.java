package com.threeping.syncday.github.query.aggregate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class GithubInstallationDTO {

    private Long id;

    @JsonProperty("installation_id")
    private Long installationId;

    @JsonProperty("account_id")
    private Long accountId;

    @JsonProperty("account_name")
    private String accountName;

    @JsonProperty("account_type")
    private String accountType;

    @JsonProperty("avatar_url")
    private String avatarUrl;

    @JsonProperty("html_url")
    private String htmlUrl;

    @JsonProperty("status")
    private String status;

    @JsonProperty("createdAt")
    private Timestamp createdAt;

    @JsonProperty("updatedAt")
    private Timestamp updatedAt;

    @JsonProperty("user_id")
    private Long userId;
}
