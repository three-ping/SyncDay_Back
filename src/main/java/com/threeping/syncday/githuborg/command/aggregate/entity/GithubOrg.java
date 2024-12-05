package com.threeping.syncday.githuborg.command.aggregate.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.kohsuke.github.GHTargetType;

import java.sql.Timestamp;

@Entity
@Table(name="TBL_GITHUB_ORG")
@Data
public class GithubOrg {

    @Id
    @Column(name="github_org_id")
    private Long githubOrgId;

    @Column(name="encrypted_installation_id")
    private String encryptedInstallationId;

    @Column(name="org_login")
    private String orgLogin;

    @Enumerated(EnumType.STRING)
    @Column(name="org_type")
    private GHTargetType orgType;

    @Column(name="installed_at")
    private Timestamp installedAt;

    @Column(name="avatar_url")
    private String avatarUrl;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private OrgStatus orgStatus;

    @Column(name="vcs_org_url")
    private String vcsOrgUrl;

    @Column(name="last_synced_at")
    private Timestamp lastSyncedAt;

}
