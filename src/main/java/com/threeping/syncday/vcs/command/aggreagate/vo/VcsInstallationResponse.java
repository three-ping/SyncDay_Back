package com.threeping.syncday.vcs.command.aggreagate.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.threeping.syncday.proj.command.aggregate.dto.ProjDTO;
import com.threeping.syncday.vcs.command.aggreagate.entity.VCSInstallation;
import lombok.Value;

@Value
public class VcsInstallationResponse {
    @JsonProperty("vcs_installation")
    private final VCSInstallation vcsInstallation;

    @JsonProperty("proj")
    private final ProjDTO proj;

    @JsonProperty("installation_token")
    private final String installationToken;

}
