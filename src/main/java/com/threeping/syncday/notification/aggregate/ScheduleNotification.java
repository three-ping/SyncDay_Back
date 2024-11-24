package com.threeping.syncday.notification.aggregate;

import com.threeping.syncday.schedule.query.aggregate.Schedule;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ScheduleNotification {
    private Schedule schedule;
    private LocalDateTime alarmTime;
}
