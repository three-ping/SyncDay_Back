package com.threeping.syncday.cardbookmark.query.controller;

import com.threeping.syncday.cardbookmark.query.service.CardBookmarkService;
import com.threeping.syncday.common.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/card-bookmarks")
public class CardBookmarkController {

    private final CardBookmarkService cardBookmarkService;

    @Autowired
    public CardBookmarkController(CardBookmarkService cardBookmarkService) {
        this.cardBookmarkService = cardBookmarkService;
    }

    @GetMapping("/users/{userId}")
    public ResponseDTO<?> findCardBookmarksByUserID(@PathVariable("userId") Long userId){
        return ResponseDTO.ok(cardBookmarkService.getCardBookmarksByUserId(userId));
    }
}
