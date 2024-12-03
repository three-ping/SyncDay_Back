package com.threeping.syncday.vcs.command.aggreagate.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class VcsInstallationRequestVO {
    @JsonProperty("installation_id")
    private String installationId;

    @JsonProperty("org_name")
    private String orgName;

    @JsonProperty("vcs_type")
    private String vcsType;

    @JsonProperty("user_id")
    private Long userId;
}
