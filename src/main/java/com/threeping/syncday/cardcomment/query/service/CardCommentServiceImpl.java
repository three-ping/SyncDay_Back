package com.threeping.syncday.cardcomment.query.service;

import com.threeping.syncday.cardcomment.query.aggregate.dto.CardCommentDTO;
import com.threeping.syncday.cardcomment.query.repository.CardCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardCommentServiceImpl implements CardCommentService {

    private final CardCommentMapper cardCommentMapper;

    @Autowired
    public CardCommentServiceImpl(CardCommentMapper cardCommentMapper) {
        this.cardCommentMapper = cardCommentMapper;
    }

    @Override
    public List<CardCommentDTO> getCommentsByCardId(Long cardId) {
        return cardCommentMapper.selectCardCommentsByCardId(cardId);
    }

    @Override
    public CardCommentDTO getCommentById(Long cardCommentId) {
        return cardCommentMapper.selectCardCommentById(cardCommentId);
    }
}
