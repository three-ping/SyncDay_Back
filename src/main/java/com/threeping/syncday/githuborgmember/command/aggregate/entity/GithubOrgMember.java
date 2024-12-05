package com.threeping.syncday.githuborgmember.command.aggregate.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@IdClass(GithubOrgMember.class)
@Entity
@Table(name="TBL_GITHUB_ORG_MEMBER")
public class GithubOrgMember {

    @Id
    @Column(name="user_id")
    private Long userId;

    @Id
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
