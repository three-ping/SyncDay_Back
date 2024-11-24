package com.threeping.syncday.notification.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RedisSubscriber implements MessageListener {

    private final SimpMessagingTemplate messagingTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public RedisSubscriber(SimpMessagingTemplate messagingTemplate, ObjectMapper objectMapper) {
        this.messagingTemplate = messagingTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            String receivedMessage = new String(message.getBody());
            String channel = new String(pattern);

            log.info("Received Message: '{}' from channel: '{}'", receivedMessage, channel);

            // 메시지를 JSON에서 UserNotification 객체로 변환
            UserNotification notification = objectMapper.readValue(receivedMessage, UserNotification.class);

            // 웹소켓을 통해 사용자에게 알림 메시지 전송
            messagingTemplate.convertAndSend("/topic/user/" + notification.getUserId(), notification.getMessage());
        } catch (Exception e) {
            log.error("Failed to process message from Redis", e);
        }
    }
}
