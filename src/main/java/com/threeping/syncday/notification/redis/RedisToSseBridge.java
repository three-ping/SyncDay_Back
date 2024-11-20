package com.threeping.syncday.notification.redis;

import com.threeping.syncday.notification.controller.NotificationController;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class RedisToSseBridge implements MessageListener {
    private final NotificationController notificationController;

    public RedisToSseBridge(NotificationController notificationController){
        this.notificationController = notificationController;
    }


    @Override
    public void onMessage(Message message, byte[] pattern) {
        String notification = new String(message.getBody());
        notificationController.sendNotification(notification);
    }
}
