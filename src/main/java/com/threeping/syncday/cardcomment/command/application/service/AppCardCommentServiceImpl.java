package com.threeping.syncday.cardcomment.command.application.service;

import com.threeping.syncday.card.command.aggregate.vo.RequestDeleteCardVO;
import com.threeping.syncday.cardcomment.command.aggregate.dto.CardCommentDTO;
import com.threeping.syncday.cardcomment.command.aggregate.entity.CardComment;
import com.threeping.syncday.cardcomment.command.aggregate.vo.RequestCreateCardCommentVO;
import com.threeping.syncday.cardcomment.command.aggregate.vo.RequestUpdateCardCommentVO;
import com.threeping.syncday.cardcomment.command.domain.repository.CardCommentRepository;
import com.threeping.syncday.common.exception.CommonException;
import com.threeping.syncday.common.exception.ErrorCode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class AppCardCommentServiceImpl implements AppCardCommentService {

    private final CardCommentRepository cardCommentRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AppCardCommentServiceImpl(CardCommentRepository cardCommentRepository, ModelMapper modelMapper) {
        this.cardCommentRepository = cardCommentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CardCommentDTO addCardComment(RequestCreateCardCommentVO cardComment) {
        CardComment cardCommentToAdd = modelMapper.map(cardComment, CardComment.class);
        CardComment savedCardComment = cardCommentRepository.save(cardCommentToAdd);
        return modelMapper.map(savedCardComment, CardCommentDTO.class);
    }

    @Override
    public CardCommentDTO modifyCardComment(RequestUpdateCardCommentVO cardComment) {
        CardComment foundCardComment = cardCommentRepository.findById(cardComment.getCardCommentId()).orElse(null);
        if (foundCardComment == null) {
            throw new CommonException(ErrorCode.CARD_COMMENT_NOT_FOUND);
        } else if (!cardComment.getUserId().equals(foundCardComment.getUserId())) {
            throw new CommonException(ErrorCode.CARD_COMMENT_UNAUTHORIZED_REQUEST);
        }
        foundCardComment.setContent(cardComment.getContent());
        foundCardComment.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        CardComment savedCardComment = cardCommentRepository.save(foundCardComment);
        return modelMapper.map(savedCardComment, CardCommentDTO.class);
    }

    @Override
    public Boolean removeCardComment(RequestDeleteCardVO cardComment) {
        CardComment foundCardComment = cardCommentRepository.findById(cardComment.getCardCommentId()).orElse(null);
        if (foundCardComment == null) {
            throw new CommonException(ErrorCode.CARD_COMMENT_NOT_FOUND);
        }
        cardCommentRepository.delete(foundCardComment);
        return Boolean.TRUE;
    }
}
