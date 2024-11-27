package com.threeping.syncday.card.command.application.service;

import com.threeping.syncday.card.command.aggregate.dto.CardDTO;
import com.threeping.syncday.card.command.aggregate.vo.RequestDeleteCardVO;
import com.threeping.syncday.card.command.aggregate.vo.RequestUpdateCardVO;

public interface AppCardService {
    CardDTO addCard(RequestUpdateCardVO newCard);

    CardDTO modifyCard(RequestUpdateCardVO updateCard);

    Boolean removeCard(RequestDeleteCardVO deleteCard);
}
