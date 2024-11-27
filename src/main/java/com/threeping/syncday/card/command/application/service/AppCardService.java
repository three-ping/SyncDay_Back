package com.threeping.syncday.card.command.application.service;

import com.threeping.syncday.card.command.aggregate.dto.CardDTO;
import com.threeping.syncday.card.command.aggregate.vo.AppCardVO;

public interface AppCardService {
    CardDTO addCard(AppCardVO newCard);
}
