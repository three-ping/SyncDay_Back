package com.threeping.syncday.vcs.command.aggregate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.threeping.syncday.vcs.command.aggregate.entity.VcsType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VcsOrgDTO {

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("vcs_type")
    private VcsType vcsType;

    @JsonProperty("org_name")
    private String orgName;

    @JsonProperty("org_url")
    private String orgUrl;

    @JsonProperty("avatar_url")
    private String avatarUrl;

    @JsonProperty("vcs_org_id")
    private Long vcsOrgId;

    @JsonProperty("installation_id")
    private Long installationId;

    @JsonProperty("description")
    private String description;
}
