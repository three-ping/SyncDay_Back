package com.threeping.syncday.card.command.application.service;

import com.threeping.syncday.card.command.aggregate.dto.CardDTO;
import com.threeping.syncday.card.command.aggregate.entity.Card;
import com.threeping.syncday.card.command.aggregate.vo.AppCardVO;
import com.threeping.syncday.card.command.domain.repository.CardRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AppCardServiceImpl implements AppCardService {

    private final CardRepository cardRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AppCardServiceImpl(CardRepository cardRepository, ModelMapper modelMapper) {
        this.cardRepository = cardRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CardDTO addCard(AppCardVO newCard) {
        Card cardToAdd = modelMapper.map(newCard, Card.class);
        log.info("cardToAdd: {}", cardToAdd);
        Card addedCard = cardRepository.save(cardToAdd);
        return modelMapper.map(addedCard, CardDTO.class);
    }
}
