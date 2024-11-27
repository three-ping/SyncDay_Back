package com.threeping.syncday.card.command.application.service;

import com.threeping.syncday.card.command.aggregate.vo.AppCardVO;

public interface AppCardService {
    Object addCard(AppCardVO newCard);
}
