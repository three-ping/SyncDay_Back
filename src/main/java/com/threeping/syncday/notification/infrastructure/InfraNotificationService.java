package com.threeping.syncday.notification.infrastructure;

import com.threeping.syncday.schedule.query.aggregate.ScheduleDetailDTO;

public interface InfraNotificationService {
    ScheduleDetailDTO findScheduleById(Long userId, Long scheduleId);
}
