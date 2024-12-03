package com.threeping.syncday.user.aggregate.oauth.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class GithubAppInstallationRequestVO {

    @NotNull(message = "Installation ID is required")
    @JsonProperty("installation_id")
    Long installationId;

    @JsonProperty("user_id")
    String userId;
}
