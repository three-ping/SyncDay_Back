package com.threeping.syncday.notification.service;

import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TestUserSchedule {
    private Long scheduleId;
    private Long userId;
    private Timestamp notificationTime;
}
