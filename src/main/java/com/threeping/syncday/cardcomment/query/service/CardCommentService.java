package com.threeping.syncday.cardcomment.query.service;


import com.threeping.syncday.cardcomment.query.aggregate.dto.CardCommentDTO;

import java.util.List;

public interface CardCommentService {
    List<CardCommentDTO> getCommentsByCardId(Long cardId);
}
