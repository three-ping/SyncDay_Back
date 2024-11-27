package com.threeping.syncday.cardcomment.command.application.controller;

import com.threeping.syncday.cardcomment.command.application.service.AppCardCommentService;
import com.threeping.syncday.cardcomment.command.aggregate.vo.RequestAddCardCommentVO;
import com.threeping.syncday.common.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/card-comments")
public class AppCardCommentController {

    private final AppCardCommentService appCardCommentService;

    @Autowired
    public AppCardCommentController(AppCardCommentService appCardCommentService) {
        this.appCardCommentService = appCardCommentService;
    }

    @PutMapping("/")
    public ResponseDTO<?> createCardComent(@RequestBody RequestAddCardCommentVO cardComment) {
        return ResponseDTO.ok(appCardCommentService.addCardComment(cardComment));
    }
}
