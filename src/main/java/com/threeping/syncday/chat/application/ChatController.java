package com.threeping.syncday.chat.application;

import com.threeping.syncday.chat.dto.ChatMessageDTO;
import com.threeping.syncday.chat.dto.ChatRoomDTO;
import com.threeping.syncday.chat.entity.ChatRoom;
import com.threeping.syncday.chat.entity.ChatType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ChatController {

        private final ChatService chatService;

    @Autowired
    public ChatController ( ChatService chatService ) {
        this.chatService = chatService;
    }

    // 메세지 전송: "/app/message"로 보낸 메시지를 처리
        @MessageMapping("/room/message/{roomId}") // "/app/{roomId}/message"로 들어오는 정보 처리
        @SendTo("/topic/room/message/{roomId}")  // 받은 반환값을 이 경로로 보내줌
        public void sendMessage(ChatMessageDTO chatMessageDTO,
                                @DestinationVariable String roomId,
                                SimpMessageHeaderAccessor headerAccessor) {
            log.info("새 메세지 in {} room: {}", roomId, chatMessageDTO);
            if(ChatType.ENTER.equals(chatMessageDTO.getChatType())) {
                headerAccessor.getSessionAttributes().put("username", chatMessageDTO.getSenderId());
                headerAccessor.getSessionAttributes().put("roomId", roomId);
                chatMessageDTO.setMessage(chatMessageDTO.getSenderId() + "님이 입장하셨습니다.");
            }

            // 메시지를 저장하거나 추가 처리가 필요하면 서비스 호출
            chatService.createMessage(roomId, chatMessageDTO);
        }

    // 채팅방 목록 조회
    @GetMapping("/room")
    public List<ChatRoom> findAllChatRooms(@RequestParam Long userId) {
        return chatService.findUserChat(userId);
    }

    // 채팅방 생성
    @PostMapping("/room/create")
    public ChatRoom createChatRoom(@RequestBody ChatRoomDTO chatRoomDTO){
        return chatService.createChatRoom(chatRoomDTO);
    }

    // 채팅방 입장
    @MessageMapping("/room/{roomId}/join")
    public void joinChatRoom(@DestinationVariable String roomId, String userId) {
        log.info("{}user: {} 채팅방 입장.", userId, roomId);
//        messagingTemplate.convertAndSend("/topic/room/" + roomId, "User" + userId + "joined!");
    }

    @PostMapping("/room/{roomId}/leave")
    public ChatRoom leaveChatRoom(@PathVariable String roomId, @RequestParam Long userId) {
        return chatService.leaveChatRoom(roomId, userId))}



 }
