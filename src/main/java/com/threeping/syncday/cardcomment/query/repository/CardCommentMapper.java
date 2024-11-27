package com.threeping.syncday.cardcomment.query.repository;

import com.threeping.syncday.cardcomment.query.aggregate.dto.CardCommentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface CardCommentMapper {
    List<CardCommentDTO> selectCardCommentsByCardId(Long cardId);
    CardCommentDTO selectCardCommentById(Long cardId);
}
