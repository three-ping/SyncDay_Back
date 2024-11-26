package com.threeping.syncday.notification.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.listener.ChannelTopic;

@Configuration
public class RedisNotificationTopicConfig {

    @Bean
    public ChannelTopic notificationTopic() {
        return new ChannelTopic("__keyevent@0__:expired");
    }
}
