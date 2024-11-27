package com.threeping.syncday.card.command.application.controller;

import com.threeping.syncday.card.command.aggregate.vo.AppCardVO;
import com.threeping.syncday.card.command.application.service.AppCardService;
import com.threeping.syncday.common.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cards")
public class AppCardController {

    private final AppCardService appCardService;

    @Autowired
    public AppCardController(AppCardService appCardService) {
        this.appCardService = appCardService;
    }

    @PostMapping("/")
    public ResponseDTO<?> createCard(@RequestBody AppCardVO newCard){
        return ResponseDTO.ok(appCardService.addCard(newCard));
    }

}
