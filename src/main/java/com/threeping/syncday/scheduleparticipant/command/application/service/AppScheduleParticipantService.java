package com.threeping.syncday.scheduleparticipant.command.application.service;

import java.util.List;

public interface AppScheduleParticipantService {
    void addScheduleParticipant(Long userId, Long scheduleId, List<Long> attendeeIds);

    void updateScheduleParticipant(Long userId, Long scheduleId, List<Long> attendeeIds);
}
