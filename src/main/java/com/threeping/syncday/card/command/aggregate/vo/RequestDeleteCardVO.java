package com.threeping.syncday.card.command.aggregate.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class RequestDeleteCardVO {
    private Long cardId;
    private Long userId;
}
