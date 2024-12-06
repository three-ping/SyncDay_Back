package com.threeping.syncday.github.command.aggregate.payload;

import com.threeping.syncday.github.command.aggregate.enums.InstallationStatus;
import lombok.Builder;
import lombok.Value;
import org.kohsuke.github.GHTargetType;

import java.time.LocalDateTime;
@Builder
@Value
public class GithubInstallationDetails {
    private Long id;
    private Long installationId;
    private Long accountId;
    private String accountName;
    private GHTargetType accountType;
    private String avatarUrl;
    private String htmlUrl;
    private InstallationStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
