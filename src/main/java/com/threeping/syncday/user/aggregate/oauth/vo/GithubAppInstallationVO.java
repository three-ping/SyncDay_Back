package com.threeping.syncday.user.aggregate.oauth.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class GithubAppInstallationVO {

    @JsonProperty("installation_id")
    Long installationId;

    @JsonProperty("code")
    String code;
}
