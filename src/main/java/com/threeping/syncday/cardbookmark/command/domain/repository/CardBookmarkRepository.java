package com.threeping.syncday.cardbookmark.command.domain.repository;

import com.threeping.syncday.cardbookmark.command.aggregate.entity.CardBookmark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardBookmarkRepository extends JpaRepository<CardBookmark, Long> {
    Boolean existsByUserIdAndCardId(Long userId, Long cardId);

    Boolean deleteByUserIdAndCardId(Long userId, Long cardId);
}
