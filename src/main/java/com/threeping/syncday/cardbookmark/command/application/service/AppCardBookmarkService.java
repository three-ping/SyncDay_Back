package com.threeping.syncday.cardbookmark.command.application.service;

import com.threeping.syncday.cardbookmark.command.aggregate.dto.CardBookmarkDTO;

public interface AppCardBookmarkService {
    CardBookmarkDTO addBookmark(Long userId, Long cardId);
    Boolean removeBookmark(Long userId, Long cardId);
}
