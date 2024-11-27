package com.threeping.syncday.cardboard.query.aggregate;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Cardboard {

    private String cardBoardId;

    private String title;

    private Timestamp createdAt;

    private Timestamp startTime;

    private Timestamp endTime;

    private Byte progressStatus;

    private String vcsType;

    private String vcsMilestoneUrl;

    private String workspaceId;

}
