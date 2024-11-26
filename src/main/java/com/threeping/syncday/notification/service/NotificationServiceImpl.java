package com.threeping.syncday.notification.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.threeping.syncday.notification.redis.RedisNotificationStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService{

    private final RedisNotificationStorage redisNotificationStorage;

    @Autowired
    public NotificationServiceImpl(RedisNotificationStorage redisNotificationStorage){
        this.redisNotificationStorage = redisNotificationStorage;
    }
    @Override
    public void sendScheduleNotification(Long userId, Long scheduleId, Timestamp notificationTime){

        String redisKey = "schedule_notification:" + userId + ":" + scheduleId;
        String redisValue = userId + ":" + scheduleId;
        long ttlInSeconds = Duration
                .between(LocalDateTime.now(),notificationTime.toLocalDateTime().minusHours(9))
                .getSeconds();
        log.info("now:{}, notice time: {}",LocalDateTime.now(),notificationTime.toLocalDateTime());
        if (ttlInSeconds > 0) {
            redisNotificationStorage.storeWithTTL(redisKey,redisValue,ttlInSeconds);
            log.info("Notification scheduled for user: '{}' with TTL of {} seconds", userId, ttlInSeconds);
        } else {
            log.warn("TTL for schedule ID '{}' is already expired or invalid.", scheduleId);
        }

    }

}
