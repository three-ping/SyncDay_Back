package com.threeping.syncday.proj.query.aggregate.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class ProjectSearchResponse {
    private Long projectId;
    private String projectName;
    private String vcsType;
    private String createAt;
}
