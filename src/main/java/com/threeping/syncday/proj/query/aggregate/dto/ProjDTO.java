package com.threeping.syncday.proj.query.aggregate.dto;

import lombok.*;

import java.sql.Timestamp;

@Data
public class ProjDTO {
    Long projId;
    String projName;
    Timestamp startTime;
    Timestamp endTime;
    Timestamp createdAt;
    Byte progressStatus;
    String vcsType;
    String vcsProjUrl;
}
