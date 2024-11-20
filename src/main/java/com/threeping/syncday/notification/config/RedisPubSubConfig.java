package com.threeping.syncday.notification.config;

import com.threeping.syncday.notification.redis.RedisMessageSubscriber;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;


@Configuration
public class RedisPubSubConfig {

    @Bean
    public MessageListenerAdapter messageListener(){
        return new MessageListenerAdapter((new RedisMessageSubscriber()));
    }

    @Bean
    public RedisMessageListenerContainer redisContainer(RedisConnectionFactory connectionFactory,
                                                        MessageListenerAdapter messageListener){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(messageListener,new ChannelTopic("notification"));
        return container;
    }

    @Bean
    public ChannelTopic topic(){
        return new ChannelTopic("notification");
    }
}
