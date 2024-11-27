package com.threeping.syncday.card.command.aggregate.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class RequestDeleteCardVO {
    @JsonProperty("card_comment_id")
    private Long cardCommentId;

    @JsonProperty("user_id")
    private Long userId;
}
