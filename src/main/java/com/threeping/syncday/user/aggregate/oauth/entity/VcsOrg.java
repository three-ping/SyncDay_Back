package com.threeping.syncday.user.aggregate.oauth.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name="TBL_VCS_ORG")
@Data
public class VcsOrg {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_id")
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(name="vcs_type", nullable=false)
    private VcsType vcsType;

    @Column(name="org_name", nullable = false)
    private String orgName;

    @Column(name="org_url", nullable = false)
    private String orgUrl;

    @Column(name="avatar_url")
    private String avatarUrl;

    @Column(name="vcs_org_id", nullable = false)
    private Long vcsOrgId;

    @Column(name = "installation_id", nullable = false)
    private Long installationId;

    @Column(name="description",columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private VcsOrgStatus status = VcsOrgStatus.ACTIVE;

    @Column(name="created_at", updatable = false)
    private Timestamp createdAt;

    @Column(name="updated_at")
    private Timestamp updatedAt;

}
