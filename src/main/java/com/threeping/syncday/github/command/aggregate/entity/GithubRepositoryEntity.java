package com.threeping.syncday.github.command.aggregate.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_github_repository")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicUpdate
public class GithubRepositoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "installation_id")
    private Long installationId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name="repo_id", nullable = false)
    private Long repoId;

    @Column(name = "repo_name", nullable = false)
    private String repoName;

    @Column(name = "repo_full_name", nullable = false)
    private String repoFullName;

    @Column(name = "repo_url", nullable = false)
    private String repoUrl;

    @Column(name = "html_url")
    private String htmlUrl;


    @Column(name = "default_branch")
    private String defaultBranch;

    @Column(name = "is_private")
    private Boolean isPrivate;

    @Column(name = "is_active")
    private Boolean isActive;

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
}
