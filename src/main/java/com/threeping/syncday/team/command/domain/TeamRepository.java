package com.threeping.syncday.team.command.domain;

import com.threeping.syncday.team.command.aggregate.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Team findByTeamId(Long teamId);
}
