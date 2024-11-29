package com.threeping.syncday.cardcomment.command.aggregate.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RequestUpdateCardCommentVO {

    @JsonProperty("content")
    @NotNull(message = "내용은 필수입니다.")
    private String content;

    @JsonProperty("user_id")
    @NotNull(message = "사용자 ID는 필수입니다.")
    private Long userId;

    @JsonProperty("card_comment_id")
    @NotNull(message = "카드 댓글 ID는 필수입니다.")
    private Long cardCommentId;


}