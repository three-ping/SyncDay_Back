package com.threeping.syncday.notification.service;

import java.sql.Timestamp;

public interface NotificationService {
    void sendScheduleNotification(Long userId, Long scheduleId, Timestamp notificationTime);
}
