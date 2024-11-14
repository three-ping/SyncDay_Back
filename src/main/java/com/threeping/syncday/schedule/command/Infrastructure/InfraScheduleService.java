package com.threeping.syncday.schedule.command.Infrastructure;

import java.util.List;

public interface InfraScheduleService {
    void requestAddScheduleParticipant(Long userId, Long scheduleId, List<Long> attendeeIds);

    void requestUpdateScheduleParticipant(Long userId, Long scheduleId, List<Long> attendeeIds);
}
