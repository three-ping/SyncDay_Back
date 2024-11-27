package com.threeping.syncday.cardcomment.command.aggregate.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class CardCommentDTO {

    @JsonProperty("card_comment_id")
    private Long cardCommentId;

    @JsonProperty("content")
    private String content;

    @JsonProperty("created_at")
    private Timestamp createdAt;

    @JsonProperty("updated_at")
    private Timestamp updatedAt;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("card_id")
    private Long cardId;
}
