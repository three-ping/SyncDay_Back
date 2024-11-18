package com.threeping.syncday.chat.controller;

import com.threeping.syncday.chat.dto.ChatMessageDTO;
import com.threeping.syncday.chat.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ChatController {

        private final ChatService chatService;
        private final SimpMessagingTemplate messagingTemplate; // 특정 사용자에게 메시지 전달 시 사용

    @Autowired
    public ChatController ( ChatService chatService, SimpMessagingTemplate messagingTemplate ) {
        this.chatService = chatService;
        this.messagingTemplate = messagingTemplate;
    }

    // "/app/message"로 보낸 메시지를 처리
        @MessageMapping("/message")
        @SendTo("/topic/chatroom")
        public ChatMessageDTO sendMessage( ChatMessageDTO chatMessageDTO) {
            log.info("Received message: {}", chatMessageDTO);

            // 메시지를 저장하거나 추가 처리가 필요하면 서비스 호출
            chatService.createMessage(chatMessageDTO);

            // "/topic/chatroom"으로 브로드캐스트
            return chatMessageDTO;
        }

        @MessageMapping("/room/{roomId}")
        public void sendToRoom(ChatMessageDTO chatMessageDTO, @DestinationVariable String roomId) {
            log.info("Sending message to room {}: {}", roomId, chatMessageDTO);

            // 메시지 저장 처리
            chatService.createMessage(chatMessageDTO);

            // 특정 방에 메시지 전송
            messagingTemplate.convertAndSend("/topic/room/" + roomId, chatMessageDTO);
        }
    }


