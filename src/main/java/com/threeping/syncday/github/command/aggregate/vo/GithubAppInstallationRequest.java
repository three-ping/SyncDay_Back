package com.threeping.syncday.github.command.aggregate.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class GithubAppInstallationRequest {

    @JsonProperty("code")
    private String code;

    @JsonProperty("installation_id")
    private Long installationId;

    @JsonProperty("setup_action")
    private String setupAction;
}
