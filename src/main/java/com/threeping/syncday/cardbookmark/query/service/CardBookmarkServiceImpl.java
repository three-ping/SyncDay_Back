package com.threeping.syncday.cardbookmark.query.service;

import com.threeping.syncday.cardbookmark.query.aggregate.dto.CardBookmarkDTO;
import com.threeping.syncday.cardbookmark.query.repository.CardBookmarkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardBookmarkServiceImpl implements CardBookmarkService {

    private final CardBookmarkMapper cardBookmarkMapper;

    @Autowired
    public CardBookmarkServiceImpl(CardBookmarkMapper cardBookmarkMapper) {
        this.cardBookmarkMapper = cardBookmarkMapper;
    }

    @Override
    public List<CardBookmarkDTO> getCardBookmarksByUserId(Long userId) {
        return cardBookmarkMapper.selectCardBookmarksByUserId(userId);
    }
}
