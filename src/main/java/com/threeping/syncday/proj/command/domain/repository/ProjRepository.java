package com.threeping.syncday.proj.command.domain.repository;

import com.threeping.syncday.proj.command.aggregate.entity.Proj;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjRepository extends JpaRepository<Proj, Long> {
}
