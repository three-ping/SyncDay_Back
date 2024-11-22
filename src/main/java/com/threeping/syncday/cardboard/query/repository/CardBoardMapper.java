package com.threeping.syncday.cardboard.query.repository;

import com.threeping.syncday.cardboard.query.aggregate.CardBoard;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CardBoardMapper {
    List<CardBoard> selectAllCardBoards();

    List<CardBoard> selectCardBoardsByWorkspaceId(Long workspaceId);
}
