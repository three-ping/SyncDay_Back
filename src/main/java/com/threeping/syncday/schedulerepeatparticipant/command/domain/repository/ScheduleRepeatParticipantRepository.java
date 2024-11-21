package com.threeping.syncday.schedulerepeatparticipant.command.domain.repository;

import com.threeping.syncday.schedulerepeatparticipant.command.aggregate.entity.ScheduleRepeatParticipant;
import com.threeping.syncday.schedulerepeatparticipant.command.aggregate.entity.ScheduleRepeatParticipantPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepeatParticipantRepository extends JpaRepository<ScheduleRepeatParticipant, ScheduleRepeatParticipantPK> {
}
