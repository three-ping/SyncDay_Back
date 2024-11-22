package com.threeping.syncday.card.query.service;


import com.threeping.syncday.card.query.aggregate.CardDTO;

import java.util.List;

public interface CardService {
    List<CardDTO> getAllCards();
}
