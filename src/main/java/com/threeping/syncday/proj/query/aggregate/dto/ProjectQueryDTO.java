package com.threeping.syncday.proj.query.aggregate.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ProjectQueryDTO {
    private Long projectId;
    private String projectName;
    private String vcsType;
    private Timestamp createdAt;
}
