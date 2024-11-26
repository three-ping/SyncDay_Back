package com.threeping.syncday.scheduleparticipant.command.domain.repository;

import com.threeping.syncday.scheduleparticipant.command.aggregate.entity.ScheduleParticipant;
import com.threeping.syncday.scheduleparticipant.command.aggregate.entity.ScheduleParticipantPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleParticipantRepository extends JpaRepository<ScheduleParticipant, ScheduleParticipantPK> {
    List<ScheduleParticipant> findByScheduleId(Long scheduleId);

    void deleteByScheduleIdAndUserId(Long scheduleId, Long userId);

    void deleteByScheduleId(Long scheduleId);

    ScheduleParticipant findByScheduleIdAndUserId(Long scheduleId, Long userId);
}
