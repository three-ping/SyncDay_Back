package com.threeping.syncday.notification.redis;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
@Slf4j
public class RedisMessageSubscriber implements MessageListener {
    private static final List<String> messages = new ArrayList<>();

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String messageBody = new String(message.getBody());
        messages.add(messageBody);
        log.info("Received message:{}", messageBody);
    }

}
