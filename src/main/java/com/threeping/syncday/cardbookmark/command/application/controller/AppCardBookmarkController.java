package com.threeping.syncday.cardbookmark.command.application.controller;

import com.threeping.syncday.cardbookmark.command.application.service.AppCardBookmarkService;
import com.threeping.syncday.common.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/card-bookmarks")
public class AppCardBookmarkController {

    private final AppCardBookmarkService appCardBookmarkService;

    @Autowired
    public AppCardBookmarkController(AppCardBookmarkService appCardBookmarkService) {
        this.appCardBookmarkService = appCardBookmarkService;
    }

    @PostMapping("/add")
    public ResponseDTO<?> addBookmark(@RequestParam Long userId, @RequestParam Long cardId) {
        return ResponseDTO.ok(appCardBookmarkService.addBookmark(userId, cardId));
    }

    @DeleteMapping("/remove")
    public ResponseDTO<?> removeBookmark(@RequestParam Long userId, @RequestParam Long cardId) {
        return ResponseDTO.ok(appCardBookmarkService.removeBookmark(userId, cardId));
    }
}
