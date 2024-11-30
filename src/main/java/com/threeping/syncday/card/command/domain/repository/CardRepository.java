package com.threeping.syncday.card.command.domain.repository;

import com.threeping.syncday.card.command.aggregate.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
