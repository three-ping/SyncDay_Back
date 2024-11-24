package com.threeping.syncday.notification.redis;

import com.threeping.syncday.common.exception.CommonException;
import com.threeping.syncday.common.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RedisPublisher {

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisPublisher(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void publish(String channel, String message) {
        try {
            redisTemplate.convertAndSend(channel, message);
            log.info("Successfully published message: '{}' to channel: '{}'", message, channel);
        } catch (Exception e) {
            log.error("Failed to publish message: '{}' to channel: '{}'", message, channel, e);
            throw new CommonException(ErrorCode.MISSING_REQUEST_PARAMETER);
        }
    }
}
