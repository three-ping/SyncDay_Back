package com.threeping.syncday.notification.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.listener.ChannelTopic;

@Configuration
public class RedisTopicConfig {

    @Bean
    public ChannelTopic topic(){
        return new ChannelTopic("notification");
    }
}
