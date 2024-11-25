package com.threeping.syncday.card.query.aggregate;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Card {
    private Long cardId;
    private String title;
    private String content;
    private Timestamp createdAt;
    private Timestamp startTime;
    private Timestamp endTime;
    private String vcsObjectType;
    private String vcsObjectUrl;
    private Long cardBoardId;
    private Long tagId;
    private Long createdBy;
    private Long assignee;

}
