package com.threeping.syncday.schedule.command.domain.repository;

import com.threeping.syncday.schedule.command.aggregate.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Schedule findByScheduleId(Long scheduleId);
}
