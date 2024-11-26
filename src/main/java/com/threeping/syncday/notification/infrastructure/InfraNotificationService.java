package com.threeping.syncday.notification.infrastructure;

import com.threeping.syncday.schedule.query.aggregate.ScheduleDTO;
import com.threeping.syncday.scheduleparticipant.query.aggregate.ScheduleParticipantDTO;

import java.util.List;

public interface InfraNotificationService {

    ScheduleDTO findScheduleByScheduleId(Long scheduleId);

    List<ScheduleParticipantDTO> getParticipantsWithNotificationTimeInNext10Minutes();
}
