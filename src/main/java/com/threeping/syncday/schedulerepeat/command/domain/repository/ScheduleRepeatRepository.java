package com.threeping.syncday.schedulerepeat.command.domain.repository;

import com.threeping.syncday.schedulerepeat.command.aggregate.entity.ScheduleRepeat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepeatRepository extends JpaRepository<ScheduleRepeat,Long> {
}
