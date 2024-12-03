package com.threeping.syncday.vcs.command.aggreagate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "TBL_VCS_USER_INSTALLATION")
@Getter
@Setter
@NoArgsConstructor
public class VcsUserInstallation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "vcs_type", nullable = false, length = 20)
    private VcsType vcsType;

    @Column(name = "vcs_username", nullable = false)
    private String vcsUsername;

    @Column(name = "avatar_url", length = 1023)
    private String avatarUrl;

    @Column(name = "profile_url", length = 1023)
    private String profileUrl;

    @Column(name = "installation_id", nullable = false, unique = true)
    private Long installationId;

    @Column(name = "vcs_user_id", nullable = false)
    private Long vcsUserId;

    @Column(name = "email")
    private String email;

    @Column(name = "bio", columnDefinition = "TEXT")
    private String bio;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private VcsStatus status = VcsStatus.ACTIVE;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    // Constructor with required fields
    public VcsUserInstallation(Long userId, VcsType vcsType, String vcsUsername, Long installationId, Long vcsUserId) {
        this.userId = userId;
        this.vcsType = vcsType;
        this.vcsUsername = vcsUsername;
        this.installationId = installationId;
        this.vcsUserId = vcsUserId;
    }

    // Builder pattern methods
    public VcsUserInstallation avatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
        return this;
    }

    public VcsUserInstallation profileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
        return this;
    }

    public VcsUserInstallation email(String email) {
        this.email = email;
        return this;
    }

    public VcsUserInstallation bio(String bio) {
        this.bio = bio;
        return this;
    }

    public VcsUserInstallation status(VcsStatus status) {
        this.status = status;
        return this;
    }

}
