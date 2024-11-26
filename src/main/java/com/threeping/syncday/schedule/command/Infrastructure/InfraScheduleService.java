package com.threeping.syncday.schedule.command.Infrastructure;

import java.sql.Timestamp;
import java.util.List;

public interface InfraScheduleService {
    void requestAddScheduleParticipant(Long userId, Long scheduleId, List<Long> attendeeIds, Timestamp notificationTime);

    void requestUpdateScheduleParticipant(Long userId, Long scheduleId, List<Long> attendeeIds);
}
