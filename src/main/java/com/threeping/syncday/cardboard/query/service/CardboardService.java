package com.threeping.syncday.cardboard.query.service;

import com.threeping.syncday.cardboard.query.aggregate.CardboardDTO;

import java.util.List;

public interface CardboardService {
    List<CardboardDTO> getAllCardBoards();

    List<CardboardDTO> getCardBoardsByWorkspaceId(Long workspaceId);
}
