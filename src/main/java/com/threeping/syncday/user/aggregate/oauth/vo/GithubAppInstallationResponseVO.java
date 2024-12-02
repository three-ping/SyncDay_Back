package com.threeping.syncday.user.aggregate.oauth.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import org.kohsuke.github.GHPermissionType;

import java.util.Map;

@Getter
@Builder
public class GithubAppInstallationResponseVO {

    @JsonProperty("token")
    private String token;

    @JsonProperty("permissions")
    private Map<String, String> permissions;

}
