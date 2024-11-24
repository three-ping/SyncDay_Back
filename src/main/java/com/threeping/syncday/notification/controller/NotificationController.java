package com.threeping.syncday.notification.controller;

import com.threeping.syncday.notification.redis.RedisPublisher;
import com.threeping.syncday.notification.redis.RedisSubscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final RedisPublisher redisPublisher;

    @Autowired
    public NotificationController(RedisPublisher redisPublisher,
                                  RedisSubscriber redisSubscriber) {
        this.redisPublisher = redisPublisher;
    }


}
