package com.threeping.syncday.cardcomment.command.domain.repository;

import com.threeping.syncday.cardcomment.command.aggregate.entity.CardComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardCommentRepository extends JpaRepository<CardComment, Long> {
}
