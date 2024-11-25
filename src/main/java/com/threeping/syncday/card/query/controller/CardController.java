package com.threeping.syncday.card.query.controller;

import com.threeping.syncday.card.query.service.CardService;
import com.threeping.syncday.common.ResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    private final CardService cardService;
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/")
    public ResponseDTO<?> findAllCards() {
        return ResponseDTO.ok(cardService.getAllCards());
    }

    @GetMapping("/cardboards/{cardboardId}")
    public ResponseDTO<?> findCardsByCardboardId(@PathVariable("cardboardId") Long cardboardId) {
        return ResponseDTO.ok(cardService.getCardsByCardboardId(cardboardId));
    }
}
