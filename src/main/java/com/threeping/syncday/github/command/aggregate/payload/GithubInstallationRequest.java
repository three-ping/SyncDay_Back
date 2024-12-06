package com.threeping.syncday.github.command.aggregate.payload;


import com.threeping.syncday.github.command.aggregate.enums.SetupAction;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class GithubInstallationRequest {

    @NotNull
    private SetupAction setupAction;

    private Long installationId;

    private String code;

    private String state;


}
