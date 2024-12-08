package com.threeping.syncday.github.command.aggregate.payload;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.threeping.syncday.github.command.aggregate.enums.SetupAction;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class GithubInstallationRequest {

    @NotNull
    @JsonProperty("setup_action")
    private SetupAction setupAction;

    @NotNull
    @JsonProperty("installation_id")
    private Long installationId;

    @NotNull
    @JsonProperty("user_id")
    private Long userId;


}
