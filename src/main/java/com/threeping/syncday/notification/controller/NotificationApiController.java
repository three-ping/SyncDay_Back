package com.threeping.syncday.notification.controller;

import com.threeping.syncday.common.ResponseDTO;
import com.threeping.syncday.notification.redis.RedisMessagePublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/notification")
public class NotificationApiController {

    private final RedisMessagePublisher redisMessagePublisher;

    public NotificationApiController(RedisMessagePublisher redisMessagePublisher){
        this.redisMessagePublisher = redisMessagePublisher;
    }

    @PostMapping("/publish")
    public ResponseDTO<?> publishNotification(@RequestBody Map<String,String> request){
        String message = request.get("message");
        redisMessagePublisher.publish(message);
        return ResponseDTO.ok("notification: " + message);
    }
}
