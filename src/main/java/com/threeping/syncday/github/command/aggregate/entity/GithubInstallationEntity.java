package com.threeping.syncday.github.command.aggregate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.time.LocalDateTime;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "tbl_github_installation")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicUpdate
public class GithubInstallationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "installation_id", nullable = false, unique = true)
    private Long installationId;

    @Column(name = "account_id", nullable = false)
    private Long accountId;

    @Column(name = "account_name", nullable = false)
    private String accountName;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type", nullable = false)
    private AccountType accountType;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "html_url")
    private String htmlUrl;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private InstallationStatus status;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public enum AccountType {
        USER, ORGANIZATION
    }

    public enum InstallationStatus {
        ACTIVE, SUSPENDED, DELETED
    }
}
