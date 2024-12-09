package com.threeping.syncday.github.query.aggregate.dto;

import lombok.Data;


import java.sql.Timestamp;

@Data
public class GithubRepositoryDTO {
    private Long id;

    private Long installationId;
    private Long userId;
    private Long repoId;
    private String repoName;
    private String repoFullName;
    private String repoUrl;

    private String htmlUrl;
    private String defaultBranch;
    private Boolean isPrivate;
    private Boolean isActive;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
