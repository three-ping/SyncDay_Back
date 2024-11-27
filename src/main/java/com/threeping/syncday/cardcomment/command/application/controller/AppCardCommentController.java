package com.threeping.syncday.cardcomment.command.application.controller;

import com.threeping.syncday.card.command.aggregate.vo.RequestDeleteCardVO;
import com.threeping.syncday.cardcomment.command.aggregate.vo.RequestUpdateCardCommentVO;
import com.threeping.syncday.cardcomment.command.application.service.AppCardCommentService;
import com.threeping.syncday.cardcomment.command.aggregate.vo.RequestCreateCardCommentVO;
import com.threeping.syncday.common.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/card-comments")
public class AppCardCommentController {

    private final AppCardCommentService appCardCommentService;

    @Autowired
    public AppCardCommentController(AppCardCommentService appCardCommentService) {
        this.appCardCommentService = appCardCommentService;
    }

    @PostMapping("/")
    public ResponseDTO<?> createCardComment(@RequestBody RequestCreateCardCommentVO cardComment) {
        return ResponseDTO.ok(appCardCommentService.addCardComment(cardComment));
    }

    @PutMapping("/")
    public ResponseDTO<?> updateCardComment(@RequestBody RequestUpdateCardCommentVO cardComment) {
        return ResponseDTO.ok(appCardCommentService.modifyCardComment(cardComment));
    }

    @DeleteMapping("/")
    public ResponseDTO<?> deleteCardComment(@RequestBody RequestDeleteCardVO cardComment) {
        return ResponseDTO.ok(appCardCommentService.removeCardComment(cardComment));
    }
}
