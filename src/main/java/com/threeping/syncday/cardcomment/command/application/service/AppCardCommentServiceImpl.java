package com.threeping.syncday.cardcomment.command.application.service;

import com.threeping.syncday.cardcomment.command.aggregate.dto.CardCommentDTO;
import com.threeping.syncday.cardcomment.command.aggregate.entity.CardComment;
import com.threeping.syncday.cardcomment.command.aggregate.vo.RequestAddCardCommentVO;
import com.threeping.syncday.cardcomment.command.domain.repository.CardCommentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public CardCommentDTO addCardComment(RequestAddCardCommentVO cardComment) {
        CardComment cardCommentToAdd = modelMapper.map(cardComment, CardComment.class);
        CardComment savedCardComment = cardCommentRepository.save(cardCommentToAdd);
        return modelMapper.map(savedCardComment, CardCommentDTO.class);
    }
}
