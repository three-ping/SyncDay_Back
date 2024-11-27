package com.threeping.syncday.cardcomment.query.aggregate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CardCommentDTO {

    @JsonProperty("card_comment_id")
    private Long cardCommentId;

    @JsonProperty("content")
    private String content;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("card_id")
    private Long cardId;

}
