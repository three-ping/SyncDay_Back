package com.threeping.syncday.notification.config;

import com.threeping.syncday.notification.redis.RedisNotificationSubscriber;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

@Configuration
public class RedisNotificationListenerConfig {

    private final RedisMessageListenerContainer container;
    private final RedisNotificationSubscriber redisNotificationSubscriber;
    private final ChannelTopic notificationTopic;

    public RedisNotificationListenerConfig(RedisMessageListenerContainer container,
                                           RedisNotificationSubscriber redisNotificationSubscriber,
                                           ChannelTopic notificationTopic){
        this.container = container;
        this.redisNotificationSubscriber = redisNotificationSubscriber;
        this.notificationTopic = notificationTopic;
    }

    @PostConstruct
    public void init(){
        container.addMessageListener(redisNotificationSubscriber,notificationTopic);
    }
}
