package com.threeping.syncday.github.query.aggregate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GithubMilestoneDTO {
    private Long id;
    private Long number;
    private Long repoId;
    private String title;
    private String description;
    private String state;
    private Integer openIssues;
    private Integer closedIssues;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime dueOn;
    private String htmlUrl;
}

