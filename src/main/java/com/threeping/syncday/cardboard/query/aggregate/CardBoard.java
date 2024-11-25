package com.threeping.syncday.cardboard.query.aggregate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class CardBoard {

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
