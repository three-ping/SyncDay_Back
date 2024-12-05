package com.threeping.syncday.githuborg.query.aggregate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.kohsuke.github.GHTargetType;

import java.sql.Timestamp;

@Data
public class GithubOrgDTO {
    @JsonProperty("github_org_id")
    private Long githubOrgId;

    @JsonProperty("encrypted_installation_id")
    private Long encrypted_installationId;

    @JsonProperty("org_login")
    private String orgLogin;

    @JsonProperty("org_type")
    private String orgType;

    @JsonProperty("avatar_url")
    private String avatarUrl;

    @JsonProperty("status")
    private String status;

    @JsonProperty("vcs_org_url")
    private String vcsOrgUrl;

    @JsonProperty("last_synced_at")
    private Timestamp lastSyncedAt;


}
