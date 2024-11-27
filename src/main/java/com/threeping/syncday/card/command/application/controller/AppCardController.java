package com.threeping.syncday.card.command.application.controller;

import com.threeping.syncday.card.command.aggregate.vo.RequestDeleteCardVO;
import com.threeping.syncday.card.command.aggregate.vo.RequestUpdateCardVO;
import com.threeping.syncday.card.command.application.service.AppCardService;
import com.threeping.syncday.common.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cards")
public class AppCardController {

    private final AppCardService appCardService;

    @Autowired
    public AppCardController(AppCardService appCardService) {
        this.appCardService = appCardService;
    }

    @PostMapping("/")
    public ResponseDTO<?> createCard(@RequestBody RequestUpdateCardVO newCard){
        return ResponseDTO.ok(appCardService.addCard(newCard));
    }

    @PutMapping("/")
    public ResponseDTO<?> updateCard(@RequestBody RequestUpdateCardVO updateCard){
        return ResponseDTO.ok(appCardService.modifyCard(updateCard));
    }

    @DeleteMapping("/")
    public ResponseDTO<?> deleteCard(@RequestBody RequestDeleteCardVO deleteCard){
        return ResponseDTO.ok(appCardService.removeCard(deleteCard));
    }
}
