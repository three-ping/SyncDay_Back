package com.threeping.syncday.vcs.command.aggregate.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.threeping.syncday.vcs.common.enums.VcsType;
import lombok.Value;

@Value
public class VcsInstallationVO {

    @JsonProperty("vcs_type")
    private VcsType vcsType;

    @JsonProperty("installation_id")
    private String installationId;

    @JsonProperty("user_id")
    private Long userId;
}
