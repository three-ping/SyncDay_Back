package com.threeping.syncday.notification.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RedisSubscriber {

    public void onMessage(String message, String channel){
        log.info("Received Message: {} from {}",message,channel);
    }
}
