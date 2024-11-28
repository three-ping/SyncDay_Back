package com.threeping.syncday.cardtag.query.service;

import com.threeping.syncday.cardtag.query.aggregate.dto.CardTagDTO;
import com.threeping.syncday.cardtag.query.repository.CardTagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardTagServiceImpl implements CardTagService {

    private final CardTagMapper cardTagMapper;

    @Autowired
    public CardTagServiceImpl(CardTagMapper cardTagMapper) {
        this.cardTagMapper = cardTagMapper;
    }

    @Override
    public CardTagDTO getCardTagById(Long tagId) {
        return cardTagMapper.selectCardTagById(tagId);
    }
}
