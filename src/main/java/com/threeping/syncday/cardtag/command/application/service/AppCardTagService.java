package com.threeping.syncday.cardtag.command.application.service;

import com.threeping.syncday.cardtag.command.aggregate.dto.CardTagDTO;

public interface AppCardTagService {
    CardTagDTO addCardTag(CardTagDTO cardTagDTO);

    CardTagDTO modifyCardTag(CardTagDTO cardTagDTO);

    Boolean removeCardTag(Long cardTagId);
}
