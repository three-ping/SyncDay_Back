package com.threeping.syncday.vcs.command.aggreagate.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.threeping.syncday.vcs.command.aggreagate.entity.VcsType;
import lombok.Value;

@Value
public class VcsInstallationRequestVO {
    @JsonProperty("installation_id")
    private Long installationId;

    @JsonProperty("org_login")
    private String orgLogin;

    @JsonProperty("vcs_type")
    private VcsType vcsType;

    @JsonProperty("user_id")
    private Long userId;
    
    @JsonProperty("proj_id")
    private Long projId;
}
