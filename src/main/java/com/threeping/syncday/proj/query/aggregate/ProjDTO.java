package com.threeping.syncday.proj.query.aggregate;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class ProjDTO {
    Long projId;
    String projName;
    Timestamp startTime;
    Timestamp endTime;
    Timestamp createdAt;
    byte progressStatus;
    Long userId;
}
