package com.threeping.syncday.vcs.command.aggregate.request;

import lombok.Value;

@Value
public class GithubInstallationRequest {
    private Long installationId;
    private String code;
    private Long userId;
    private SetupAction setupAction;
    private String state;
}
