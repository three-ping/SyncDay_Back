package com.threeping.syncday.chat.application;

import com.threeping.syncday.chat.dto.ChatMessageDTO;
import com.threeping.syncday.chat.entity.ChatRoom;
import com.threeping.syncday.chat.entity.ChatType;

import com.threeping.syncday.user.command.application.service.UserCommandServiceImpl;
import com.threeping.syncday.user.command.domain.aggregate.UserEntity;
import com.threeping.syncday.user.command.domain.repository.UserRepository;
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
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;
    private final UserRepository userRepository;
    private final UserCommandServiceImpl userCommandService;

    @Autowired
    public ChatController(ChatService chatService, UserRepository userRepository, UserCommandServiceImpl userCommandService) {
        this.chatService = chatService;
        this.userRepository = userRepository;
        this.userCommandService = userCommandService;
    }

    // 메세지 전송: "/app/message"로 보낸 메시지를 처리
    @MessageMapping("/room/{roomId}") // "/app/{roomId}/message"로 들어오는 정보 처리
    @SendTo("/topic/room/{roomId}")  // 받은 메세지를 이 경로로 보내줌
    public void sendMessage(@DestinationVariable String roomId, ChatMessageDTO chatMessageDTO,
                            SimpMessageHeaderAccessor headerAccessor) {
        log.info("새 메세지 in {} room: {}", roomId, chatMessageDTO);
        String senderName = userCommandService.getUserNameById(chatMessageDTO.getSenderId());
        chatMessageDTO.setSenderName(senderName);

        if (ChatType.ENTER.equals(chatMessageDTO.getChatType())) {
            headerAccessor.getSessionAttributes().put("username", chatMessageDTO.getSenderId());
            headerAccessor.getSessionAttributes().put("roomId", roomId);
            chatMessageDTO.setContent(chatMessageDTO.getSenderName() + "님이 입장하셨습니다.");
        }

        // 메시지를 저장하거나 추가 처리가 필요하면 서비스 호출
        chatService.createMessage(roomId, chatMessageDTO);
    }

    // 채팅방 목록 조회
    @GetMapping("/room")
    public List<ChatRoom> findMyChat(@RequestParam Long userId) {
        log.info("유저 채팅 목록 찾기 메서드 시작");
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다: "+userId));
        log.info("유저 {}의 채팅방 목록 조회 요청.", userId);

        List<ChatRoom> chatRoomList = chatService.findUserChat(userId);
        log.info("채팅방 리스트 엔티티: {}", chatRoomList);

        return chatRoomList;
    }

    // 특정 채팅방 조회
    @GetMapping("/room/{roomId}/message")
    public List<ChatMessageDTO> getChatRoom(@PathVariable String roomId, @RequestParam Long userId) {
        return chatService.findChatRoomByRoomId(roomId, userId);
    }

    // 채팅방 생성
    @PostMapping("/room/create")
    public ChatRoom createChatRoom(@RequestBody ChatRoom chatRoom) {
        log.info("새 채팅방 생성 요청: {}", chatRoom);
//
//        String chatRoomName = chatRoom.getChatRoomName();
//        List<Long> memberIds = chatRoom.getMemberIds();
//
//        ChatRoom room = new ChatRoom();
//        room.setChatRoomName(chatRoomName);
//        room.setMemberIds(memberIds);

        return chatService.createChatRoom(chatRoom);
    }

    // 채팅방 나가기
    @PostMapping("/room/{roomId}/leave")
    public ChatRoom leaveChatRoom(@PathVariable String roomId, @RequestParam Long userId) {
        log.info("유저 {} 채팅방 {} 나가기 요청.", userId, roomId);
        return chatService.leaveChatRoom(roomId, userId);
    }

//    // 채팅방 이름 수정
//    @PutMapping("/room/{roomId}/name")
//    public ChatRoom updateRoomName(
//            @PathVariable String roomId,
//            @RequestBody String newRoomName
//    ) {
//        log.info("채팅방 {} 이름 수정 요청: {}", roomId, newRoomName);
//        return chatService.updateRoomName(roomId, newRoomName);
//    }

    // 기존 채팅방에 멤버 초대(추가)
    @PostMapping("/room/{roomId}/invite")
    public ChatRoom inviteMember(@PathVariable String roomId, @RequestBody List<Long> userIds) {
        log.info("채팅방에 새로운 멤버 추가: ", userIds);

        return chatService.inviteUser(roomId, userIds);
    }

}

