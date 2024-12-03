package com.threeping.syncday.vcs.command.aggreagate.vo;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.threeping.syncday.vcs.command.aggreagate.entity.VcsOrgType;
import com.threeping.syncday.vcs.command.aggreagate.entity.VcsType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;
@AllArgsConstructor
@Value
@Data
public class VcsInstallationCheckRequestVO {

    @JsonProperty("vcs_type")
    private VcsType vcsType;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("vcs_org_id")
    private Long vcsOrgId;

    @JsonProperty("vcs_org_type")
    private VcsOrgType vcsOrgType;

    @JsonProperty("vcs_login")
    private String vcsLogin;

}
