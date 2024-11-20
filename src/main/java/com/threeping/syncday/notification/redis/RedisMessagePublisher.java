package com.threeping.syncday.notification.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;

@Component
public class RedisMessagePublisher {
    private final RedisTemplate<String,Object> redisTemplate;
    private final ChannelTopic topic;

    public RedisMessagePublisher(RedisTemplate<String,Object> redisTemplate,
                                 ChannelTopic channelTopic){
        this.redisTemplate = redisTemplate;
        this.topic = channelTopic;
    }

    public void publish(String message){
        redisTemplate.convertAndSend(topic.getTopic(),message);
    }
}
