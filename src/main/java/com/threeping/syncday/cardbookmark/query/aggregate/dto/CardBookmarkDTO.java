package com.threeping.syncday.cardbookmark.query.aggregate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CardBookmarkDTO {

    @JsonProperty("card_bookmark_id")
    private Long cardBookmarkId;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("card_id")
    private Long cardId;
}
