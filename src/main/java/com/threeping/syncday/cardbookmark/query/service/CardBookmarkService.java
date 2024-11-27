package com.threeping.syncday.cardbookmark.query.service;

import com.threeping.syncday.cardbookmark.query.aggregate.dto.CardBookmarkDTO;

import java.util.List;

public interface CardBookmarkService {
    List<CardBookmarkDTO> getCardBookmarksByUserId(Long userId);
}
