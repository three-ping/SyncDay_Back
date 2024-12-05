package com.threeping.syncday.githuborgmember.query.aggreagate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class GithubOrgMemberDTO {

    @JsonProperty("github_org_member_id")
    private Long githubOrgMemberId;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("github_org_id")
    private Long githubOrgId;

    @JsonProperty("github_username")
    private String githubUsername;

    @JsonProperty("membership_state")
    private String membershipState;

    @JsonProperty("role")
    private String role;

    @JsonProperty("last_synced_at")
    private Timestamp lastSyncedAt;

    @JsonProperty("connected_at")
    private Timestamp connectedAt;


}
