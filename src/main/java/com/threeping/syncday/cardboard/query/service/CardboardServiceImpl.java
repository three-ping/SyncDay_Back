package com.threeping.syncday.cardboard.query.service;

import com.threeping.syncday.cardboard.query.aggregate.Cardboard;
import com.threeping.syncday.cardboard.query.aggregate.CardboardDTO;
import com.threeping.syncday.cardboard.query.repository.CardboardMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardboardServiceImpl implements CardboardService {

    private final CardboardMapper cardBoardMapper;
    private final ModelMapper modelMapper;

    @Autowired
    public CardboardServiceImpl(CardboardMapper cardBoardMapper,
                                ModelMapper modelMapper) {
        this.cardBoardMapper = cardBoardMapper;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CardboardDTO> getAllCardBoards() {
        List<Cardboard> cardboards = cardBoardMapper.selectAllCardBoards();
        return cardboards.stream()
                .map(cardboard -> modelMapper.map(cardboard, CardboardDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CardboardDTO> getCardBoardsByWorkspaceId(Long workspaceId) {

        List<Cardboard> cardboards = cardBoardMapper.selectCardBoardsByWorkspaceId(workspaceId);
        return cardboards.stream()
                .map(cardboard -> modelMapper.map(cardboard, CardboardDTO.class))
                .collect(Collectors.toList());
    }
}
