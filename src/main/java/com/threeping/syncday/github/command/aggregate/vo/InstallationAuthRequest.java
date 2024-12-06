package com.threeping.syncday.github.command.aggregate.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
public class InstallationAuthRequest {

    @JsonProperty("code")
    private String code;

    @JsonProperty("installation_id")
    private Long installationId;

    @JsonProperty("setup_action")
    private String setupAction;

    @Builder
    public InstallationAuthRequest(String code, Long installationId, String setupAction) {
        this.code = code;
        this.installationId = installationId;
        this.setupAction = setupAction;
    }
}
