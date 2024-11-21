package com.threeping.syncday.chat.application;

import com.threeping.syncday.chat.dto.ChatMessageDTO;
import com.threeping.syncday.chat.dto.ChatRoomDTO;
import com.threeping.syncday.chat.entity.ChatRoom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
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

    // 메세지 전송: "/app/message"로 보낸 메시지를 처리
        @MessageMapping("/room/{roomId}/message") // "/app/{roomId}/message"로 들어오는 정보 처리
//        @SendTo("/topic/room/{roomId}")  // 받은 반환값을 이 경로로 보내줌
        public void sendMessage( ChatMessageDTO chatMessageDTO, @DestinationVariable String roomId) {
            log.info("새 메세지 in {} room: {}", roomId, chatMessageDTO);

            // 메시지를 저장하거나 추가 처리가 필요하면 서비스 호출
            chatService.createMessage(roomId, chatMessageDTO);
//            messagingTemplate.convertAndSend("/topic/room/" + chatMessageDTO.getRoomId(), chatMessageDTO);
            messagingTemplate.convertAndSend("/topic/room/" + roomId, chatMessageDTO);
        }

    // 채팅방 생성
    @MessageMapping("/roor/create")
    public void createChatRoom(ChatRoomDTO chatRoomDTO) {
        ChatRoom newRoom = chatService.createChatRoom(chatRoomDTO);

        messagingTemplate.convertAndSend("/topic/newroom", newRoom);
    }

    // 채팅방 입장
    @MessageMapping("/room/{roomId}/join")
    public void joinChatRoom(@DestinationVariable String roomId, String userId) {
        log.info("{}user: {} 채팅방 입장.", userId, roomId);
        messagingTemplate.convertAndSend("/topic/room/" + roomId, "User" + userId + "joined!");
    }
    }


