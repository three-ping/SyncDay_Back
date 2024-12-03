package com.threeping.syncday.vcs.command.aggreagate.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.kohsuke.github.GHTargetType;

import java.util.Date;

@Entity
@Table(name="TBL_VCS_INSTALLATION")
@Getter
@Setter
@NoArgsConstructor
@Data
public class VCSInstallation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "vcs_type", nullable = false, length = 20)
    private VcsType vcsType;

    @Column(name = "vcs_org_login", nullable = false)
    private String vcsOrgLogin;

    @Column(name = "vcs_org_url", nullable = false, length = 1023)
    private String orgUrl;

    @Enumerated(EnumType.STRING)
    @Column(name="vcs_org_type", nullable = false)
    private GHTargetType vcsTargetType;


    @Column(name = "avatar_url", length = 1023)
    private String avatarUrl;

    @Column(name = "vcs_org_id", nullable = false)
    private Long vcsOrgId;

    @Column(name = "installation_id", nullable = false, unique = true)
    private Long installationId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private VcsStatus status = VcsStatus.ACTIVE;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;


}
