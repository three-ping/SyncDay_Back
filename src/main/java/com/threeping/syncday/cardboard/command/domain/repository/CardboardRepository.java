package com.threeping.syncday.cardboard.command.domain.repository;

import com.threeping.syncday.cardboard.command.aggreate.entity.Cardboard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardboardRepository extends JpaRepository<Cardboard,Long> {
}
