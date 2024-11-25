package com.threeping.syncday.notification.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisNotificationPublisher {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ChannelTopic notificationTopic;
    private final Random random = new Random();

    @Autowired
    public RedisNotificationPublisher(RedisTemplate<String, Object> redisTemplate,
                                      ChannelTopic notificationTopic) {
        this.redisTemplate = redisTemplate;
        this.notificationTopic = notificationTopic;
    }

    @Async
    public void publishWithTTL(String key, String message, long ttlSeconds) {
        long jitter = random.nextLong(30L);
        ttlSeconds += jitter; // 30초의 분산값을 넣어서 한 번에 TTL이 만료되는 것을 방지!
        try {
            redisTemplate.opsForValue().set(key,message,ttlSeconds, TimeUnit.SECONDS);
            log.info("Successfully published message: '{}' for key: '{}'", message, key);
        } catch (Exception e) {
            log.error("Failed to publish message: '{}' for key: '{}'", message, key, e);
            log.debug(e.getMessage());
        }
    }
}
