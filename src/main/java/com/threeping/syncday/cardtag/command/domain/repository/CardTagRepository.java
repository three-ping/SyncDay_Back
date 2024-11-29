package com.threeping.syncday.cardtag.command.domain.repository;

import com.threeping.syncday.cardtag.command.aggregate.entity.CardTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardTagRepository extends JpaRepository<CardTag, Long> {
}
