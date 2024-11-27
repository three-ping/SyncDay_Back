package com.threeping.syncday.cardcomment.query.controller;

import com.threeping.syncday.cardcomment.query.service.CardCommentService;
import com.threeping.syncday.common.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/card-comments")
public class CardCommentController {

    private final CardCommentService cardCommentService;

    @Autowired
    public CardCommentController(CardCommentService cardCommentService) {
        this.cardCommentService = cardCommentService;
    }

    @GetMapping("/cards/{cardId}")
    public ResponseDTO<?> findCommentsByCardId(@PathVariable("cardId") Long cardId){
        return ResponseDTO.ok(cardCommentService.getCommentsByCardId(cardId));
    }
}
