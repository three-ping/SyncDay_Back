package com.threeping.syncday.githuborgmember.command.aggregate.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name="TBL_GITHUB_ORG_MEMBER")
public class GithubOrgMember {

    @Id
    @Column(name="github_org_member_id")
    private Long githubOrgMemberId;

    @Column(name="user_id")
    private Long userId;

    @Column(name="github_org_id")
    private Long githubOrgId;

    @Column(name="github_username")
    private String githubUsername;

    @Enumerated(EnumType.STRING)
    @Column(name="membership_state")
    private MembershipState membershipState;

    @Enumerated(EnumType.STRING)
    @Column(name="role")
    private Role role;

    @Column(name="last_synced_at")
    private Timestamp lastSyncedAt;

    @Column(name="connected_at")
    private Timestamp connectedAt;


}
