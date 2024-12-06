package com.threeping.syncday.github.command.aggregate.entity;

import com.threeping.syncday.github.command.aggregate.enums.AccountType;
import com.threeping.syncday.github.command.aggregate.enums.InstallationStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "github_installations")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GithubInstallationEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "installation_id")
    private Long installationId;

    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "account_type")
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "token_expires_at")
    private LocalDateTime tokenExpiresAt;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private InstallationStatus status;

    @Builder
    public GithubInstallationEntity(Long installationId, Long accountId, String accountName,
                                    AccountType accountType) {
        this.installationId = installationId;
        this.accountId = accountId;
        this.accountName = accountName;
        this.accountType = accountType;
        this.status = InstallationStatus.PENDING;
    }

    public void updateAccessToken(String accessToken, LocalDateTime expiresAt) {
        this.accessToken = accessToken;
        this.tokenExpiresAt = expiresAt;
    }

    public void activate() {
        this.status = InstallationStatus.ACTIVE;
    }

    public void deactivate() {
        this.status = InstallationStatus.INACTIVE;
    }

    public boolean isTokenExpired() {
        return LocalDateTime.now().isAfter(this.tokenExpiresAt);
    }
}
