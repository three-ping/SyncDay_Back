package com.threeping.syncday.notification.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RedisPublisher {

    private final RedisTemplate<String,Object> redisTemplate;
    private final ChannelTopic topic;

    @Autowired
    public RedisPublisher(RedisTemplate<String,Object> redisTemplate,
                          ChannelTopic topic){
        this.redisTemplate = redisTemplate;
        this.topic = topic;
    }

    public void publish(String message){
        redisTemplate.convertAndSend(topic.getTopic(),message);
    }
}
