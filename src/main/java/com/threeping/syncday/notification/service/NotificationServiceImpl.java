package com.threeping.syncday.notification.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.threeping.syncday.notification.redis.RedisNotificationPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService{

    private final RedisNotificationPublisher redisNotificationPublisher;
    private final ObjectMapper objectMapper;

    @Autowired
    public NotificationServiceImpl(RedisNotificationPublisher redisNotificationPublisher,
                                   ObjectMapper objectMapper){
        this.redisNotificationPublisher = redisNotificationPublisher;
        this.objectMapper = objectMapper;
    }
    @Override
    public void sendScheduleNotification(Long userId, Long scheduleId, Timestamp notificationTime){

        String redisKey = "schedule_notification:" + userId + ":" + scheduleId;
        String redisValue = userId + ":" + scheduleId;
        long ttlInSeconds = Duration
                .between(LocalDateTime.now(),notificationTime.toLocalDateTime())
                .getSeconds();
        if (ttlInSeconds > 0) {
            redisNotificationPublisher.publishWithTTL(redisKey,redisValue,ttlInSeconds);
            log.info("Notification scheduled for user: '{}' with TTL of {} seconds", userId, ttlInSeconds);
        } else {
            log.warn("TTL for schedule ID '{}' is already expired or invalid.", scheduleId);
        }

    }

}
