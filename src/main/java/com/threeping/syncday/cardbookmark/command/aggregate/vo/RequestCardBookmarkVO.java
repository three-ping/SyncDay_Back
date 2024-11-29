package com.threeping.syncday.cardbookmark.command.aggregate.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RequestCardBookmarkVO {
    @JsonProperty("user_id")
    @NotNull
    private Long userId;

    @JsonProperty("card_id")
    @NotNull
    private Long cardId;
}
