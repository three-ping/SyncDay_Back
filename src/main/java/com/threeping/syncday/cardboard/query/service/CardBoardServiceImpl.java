package com.threeping.syncday.cardboard.query.service;

import com.threeping.syncday.cardboard.query.aggregate.CardBoard;
import com.threeping.syncday.cardboard.query.aggregate.CardBoardDTO;
import com.threeping.syncday.cardboard.query.repository.CardBoardMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardBoardServiceImpl implements CardBoardService {

    private final CardBoardMapper cardBoardMapper;
    private final ModelMapper modelMapper;

    @Autowired
    public CardBoardServiceImpl(CardBoardMapper cardBoardMapper,
                                ModelMapper modelMapper) {
        this.cardBoardMapper = cardBoardMapper;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CardBoardDTO> getAllCardBoards() {
        List<CardBoard> cardBoards = cardBoardMapper.selectAllCardBoards();
        List<CardBoardDTO> cardBoardDTOs = cardBoards.stream()
                .map(cardBoard -> modelMapper.map(cardBoard, CardBoardDTO.class))
                .collect(Collectors.toList());
        return cardBoardDTOs;
    }

    @Override
    public List<CardBoardDTO> getCardBoardsByWorkspaceId(Long workspaceId) {

        List<CardBoard> cardBoards = cardBoardMapper.selectCardBoardsByWorkspaceId(workspaceId);
        List<CardBoardDTO> cardBoardDTOs = cardBoards.stream()
                .map(cardBoard -> modelMapper.map(cardBoard, CardBoardDTO.class))
                .collect(Collectors.toList());
        return cardBoardDTOs;
    }
}
