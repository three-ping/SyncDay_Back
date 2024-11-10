package com.threeping.syncday.proj.query.aggregate;

import lombok.*;

import java.sql.Timestamp;

@Data
public class Proj {
    Long projId;
    String projName;
    Timestamp startTime;
    Timestamp endTime;
    Timestamp createdAt;
    Byte progressStatus;
    Long userId;

}
