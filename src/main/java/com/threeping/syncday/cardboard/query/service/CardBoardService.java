package com.threeping.syncday.cardboard.query.service;

import com.threeping.syncday.cardboard.query.aggregate.CardBoardDTO;

import java.util.List;

public interface CardBoardService {
    List<CardBoardDTO> getAllCardBoards();

    List<CardBoardDTO> getCardBoardsByWorkspaceId(Long workspaceId);
}
