package com.threeping.syncday.notification.redis;

import com.threeping.syncday.notification.controller.NotificationSseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RedisNotificationSubscriber implements MessageListener {

    private final NotificationSseController notificationSseController;

    @Autowired
    public RedisNotificationSubscriber(NotificationSseController notificationSseController) {
        this.notificationSseController = notificationSseController;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String msg = new String(message.getBody());
        log.info("Received message: {}", msg);
        String[] parts = msg.split(":");
        Long userId = Long.valueOf(parts[1]);
        Long scheduleId = Long.valueOf(parts[2]);

        notificationSseController.sendScheduleNotification(userId, scheduleId);

    }

}
