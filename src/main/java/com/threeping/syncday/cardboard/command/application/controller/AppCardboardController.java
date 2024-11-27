package com.threeping.syncday.cardboard.command.application.controller;

import com.threeping.syncday.cardboard.command.aggreate.vo.AddCardboardVO;
import com.threeping.syncday.cardboard.command.application.service.AppCardboardService;
import com.threeping.syncday.common.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cardboards")
public class AppCardboardController {

    private final AppCardboardService appCardboardService;

    @Autowired
    public AppCardboardController(AppCardboardService appCardboardService) {
        this.appCardboardService = appCardboardService;
    }

    @PostMapping("/")
    public ResponseDTO<?> createCardBoard(@RequestBody AddCardboardVO cardboardVO) {
        return ResponseDTO.ok(appCardboardService.addCardboard(cardboardVO));
    }

}
