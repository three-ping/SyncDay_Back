package com.threeping.syncday.github.command.aggregate.payload;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GithubInstallationResponse {

    Boolean success;
    String message;
    GithubInstallationDetails installationDetails;

}
