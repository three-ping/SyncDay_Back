package com.threeping.syncday.cardtag.query.service;

import com.threeping.syncday.cardtag.query.aggregate.dto.CardTagDTO;

public interface CardTagService {
    CardTagDTO getCardTagById(Long tagId);
}
