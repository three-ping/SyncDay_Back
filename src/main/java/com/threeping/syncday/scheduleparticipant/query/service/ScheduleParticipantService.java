package com.threeping.syncday.scheduleparticipant.query.service;

import com.threeping.syncday.scheduleparticipant.query.aggregate.ScheduleParticipantDTO;

import java.util.List;

public interface ScheduleParticipantService {
    List<ScheduleParticipantDTO> getParticipantsWithNotificationTimeInNext10Minutes();
}
