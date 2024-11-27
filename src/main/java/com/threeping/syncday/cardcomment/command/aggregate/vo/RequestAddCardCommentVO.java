package com.threeping.syncday.cardcomment.command.aggregate.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestAddCardCommentVO {

    @JsonProperty("content")
    @NotNull(message = "내용은 필수입니다.")
    private String content;

    @JsonProperty("user_id")
    @NotNull(message = "사용자 ID는 필수입니다.")
    private Long userId;

    @JsonProperty("card_id")
    @NotNull(message = "카드 ID는 필수입니다.")
    private Long cardId;
}