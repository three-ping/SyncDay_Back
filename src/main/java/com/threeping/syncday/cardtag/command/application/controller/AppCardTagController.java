package com.threeping.syncday.cardtag.command.application.controller;

import com.threeping.syncday.cardtag.command.aggregate.dto.CardTagDTO;
import com.threeping.syncday.cardtag.command.application.service.AppCardTagService;
import com.threeping.syncday.common.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/card-tags")
public class AppCardTagController {

    private final AppCardTagService appCardTagService;

    @Autowired
    public AppCardTagController(AppCardTagService appCardTagService) {
        this.appCardTagService = appCardTagService;
    }

    @PostMapping("/")
    public ResponseDTO<?> createCardTag(@RequestBody CardTagDTO cardTagDTO){
        return ResponseDTO.ok(appCardTagService.addCardTag(cardTagDTO));
    }

    @PutMapping("/")
    public ResponseDTO<?> updateCardTag(@RequestBody CardTagDTO cardTagDTO){
        return ResponseDTO.ok(appCardTagService.modifyCardTag(cardTagDTO));
    }

    @DeleteMapping("/{cardTagId}")
    public ResponseDTO<?> deleteCardTag(@PathVariable("cardTagId") Long cardTagId){
        return ResponseDTO.ok(appCardTagService.removeCardTag(cardTagId));
    }
}
