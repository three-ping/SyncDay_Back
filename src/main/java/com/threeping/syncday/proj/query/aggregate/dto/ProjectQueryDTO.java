package com.threeping.syncday.proj.query.aggregate.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProjectQueryDTO {
    private Long projectId;
    private String projectName;
    private String description;
    private String vcsType;
    private LocalDateTime createdAt;
}
