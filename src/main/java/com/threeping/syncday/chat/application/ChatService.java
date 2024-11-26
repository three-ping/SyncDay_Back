package com.threeping.syncday.chat.application;

import com.threeping.syncday.chat.dto.ChatMessageDTO;
import com.threeping.syncday.chat.dto.ChatRoomDTO;
import com.threeping.syncday.chat.entity.ChatMessage;
import com.threeping.syncday.chat.entity.ChatRoom;
import com.threeping.syncday.chat.entity.ChatType;
import com.threeping.syncday.chat.repository.ChatMessageRepository;
import com.threeping.syncday.chat.repository.ChatRoomRepository;
import com.threeping.syncday.user.command.domain.aggregate.UserEntity;
import com.threeping.syncday.user.command.domain.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ChatService {

    private static final Logger log = LoggerFactory.getLogger(ChatService.class);
    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;
    private final SimpMessagingTemplate messagingTemplate; // 특정 사용자에게 메시지 전달 시 사용


    @Autowired
    public ChatService(ChatMessageRepository chatMessageRepository,
                       ChatRoomRepository chatRoomRepository,
                       UserRepository userRepository,
                       SimpMessagingTemplate messagingTemplate) {
        this.chatMessageRepository = chatMessageRepository;
        this.chatRoomRepository = chatRoomRepository;
        this.userRepository = userRepository;
        this.messagingTemplate = messagingTemplate;
    }


    //  채팅방 목록 조회
    public List<ChatRoom> findUserChat(Long userId) {
        log.info("유저 채팅 목록 찾는 서비스 메서드 시작");
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다.: " + userId));

        List<ChatRoom> chatRoomList = chatRoomRepository.findChatRoomsByMemberIdsContaining(userId);
        log.info("채팅룸 리스트 엔티티: {}", chatRoomList);

        return chatRoomList;
    }

    //  특정 채팅방 조회
    public ChatRoom findChatRoomByRoomId(String roomId) {

        return chatRoomRepository.findChatRoomByRoomId(roomId).orElseThrow(
                () -> new IllegalArgumentException("해당 {roomId}채팅방이 없습니다." + roomId));

    }

    // 멤버 조회
//    public List<UserEntity> getAllUsers() {
//        return userRepository.findAll();
//    }

    //  채팅방 생성(멤버 필터)
    public ChatRoom createChatRoom(ChatRoomDTO chatRoomDTO) {
        // 멤버 기반 이름 설정
//            List<String> memberIds = chatRoomDTO.getMemberIds();
//            String chatRoomName = memberIds.size() == 2
//                    ? "1:1 채팅 - " + memberIds.get(0) + " & " + memberIds.get(1)
//                    : String.join(", ", memberIds);
//
//            // 1:1 채팅방 중복 방지
//            if (memberIds.size() == 2) {

//                ChatRoom existingRoom = chatRoomRepository.find1to1ChatRoom(memberIds.get(0), memberIds.get(1));
//                if (existingRoom != null) {
//                    return existingRoom; // 이미 존재하면 해당 방 반환
//                }
//            }
        List<UserEntity> userList = userRepository.findAllById(chatRoomDTO.getMemberIds());

        String roomName = userList.stream()
                .map(UserEntity::getUserName)
                .collect(Collectors.joining(", "));


        ChatRoom chatRoom = ChatRoom.builder()
                .roomId(chatRoomDTO.getRoomId())
                .chatRoomName(roomName)
                .creatorId(chatRoomDTO.getCreatorId())
                .createdAt(LocalDateTime.now())
                .memberCount(chatRoomDTO.getMemberIds().size())
                .memberIds(chatRoomDTO.getMemberIds())
                .build();
        return chatRoomRepository.save(chatRoom);
    }


    //  특정 채팅방 퇴장
    public ChatRoom leaveChatRoom(String roomId, Long userId) {
        ChatRoom chatRoom = chatRoomRepository.findChatRoomByRoomId(roomId)
                .orElseThrow(() -> new IllegalArgumentException("채팅방이 존재하지 않습니다."));

        List<Long> memberIds = new ArrayList<>(chatRoom.getMemberIds());
        memberIds.remove(userId);
        chatRoom.setMemberIds(memberIds);

        LocalDateTime leaveTime = LocalDateTime.now();  // 채팅방 나간 시간

        ChatMessage leaveMessage = new ChatMessage();
        leaveMessage.setRoomId(roomId);
        leaveMessage.setSenderId(userId);
        leaveMessage.setChatType(ChatType.LEAVE);
        leaveMessage.setSentTime(leaveTime);
        chatMessageRepository.save(leaveMessage);
        ChatRoom updatedChatRoom = chatRoomRepository.save(chatRoom);

        if (chatRoom.getMemberCount() == 0) {   // 채팅방에 멤버가 1명이면 자동 삭제됨
            chatRoomRepository.delete(chatRoom);
        } else {
            chatRoomRepository.save(chatRoom);
        }

        return updatedChatRoom;
    }


    // 멤버 초대
//    public ChatRoom inviteUser(String roomId, List<Long> userIds ) {
//        ChatRoom chatRoom = chatRoomRepository.findChatRoomByRoomId(roomId)
//                .orElseThrow(() -> new RuntimeException("해당 채팅방이 존재하지 않습니다."));
//
//        List<String> memberIds = new ArrayList<>(chatRoom.getMemberIds());
//        memberIds.addAll(userIds);
//        chatRoom.setMemberIds(memberIds);
//
//        ChatRoom updatedRoom = chatRoomRepository.save(chatRoom);
//
//        // 초대된 사용자들의 입장 메시지 생성 및 저장
//        for (String userName : userIds) {
//            UserEntity user = userRepository.findByUserId(userIds)
//                    .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
//
//            ChatMessage enterMessage = new ChatMessage();
//            enterMessage.setRoomId(roomId);
////            enterMessage.setSenderId(senderId);
//            enterMessage.setMessage(user.getUserName() + "님이 입장하셨습니다.");
//            enterMessage.setChatType(ChatType.ENTER);
//            enterMessage.setSentTime(LocalDateTime.now());
//            ChatMessage savedMessage = chatMessageRepository.save(enterMessage);
//
//            // 입장 메시지를 WebSocket을 통해 전송
//            ChatMessageDTO chatMessage = convertToDto(savedMessage);
//            messagingTemplate.convertAndSend("/topic/messages/" + roomId, chatMessage);
//    }
//        // 채팅방 정보 업데이트를 모든 참여자에게 브로드캐스트
//        messagingTemplate.convertAndSend("/topic/rooms/" + roomId + "/update", updatedRoom);
//
//        // 새로 초대된 사용자들에게 채팅방 목록 업데이트 알림
//        userIds.forEach(userId -> {
//            messagingTemplate.convertAndSend("/topic/user/" + userId + "/rooms/update", findUserChat());
//        });
//
//        return updatedRoom;
//    }

        // 채팅방 이름 수정(단체톡방 일 시)
        public ChatRoom updateRoomName (String roomId, String newRoom) {
            ChatRoom chatRoom = chatRoomRepository.findChatRoomByRoomId(roomId)
                    .orElseThrow(() -> new IllegalArgumentException("채팅방이 존재하지 않습니다."));

            chatRoom.setChatRoomName(newRoom);
            return chatRoomRepository.save(chatRoom);
        }



//  채팅방 채팅 작성
        public void createMessage(String roomId, ChatMessageDTO chatMessageDTO) {

    ChatMessage chatMessage = new ChatMessage();
    chatMessage.setRoomId(roomId);
    chatMessage.setSenderId(chatMessage.getSenderId());
    chatMessage.setMessage(chatMessage.getMessage());
    chatMessage.setMessageId(chatMessage.getMessageId());
    chatMessage.setMemberCount(chatMessage.getMemberCount());
    chatMessage.setMemberIds(chatMessage.getMemberIds());
    chatMessage.setSentTime(LocalDateTime.now());
    chatMessage.setChatType(chatMessageDTO.getChatType());

    chatMessageRepository.save(chatMessage);
        }

    // 메세지 db에 저장
    private ChatMessageDTO convertToDto(ChatMessage chat) {
            ChatMessageDTO messageDTO =  new ChatMessageDTO();
            messageDTO.setRoomId(chat.getRoomId());
            messageDTO.setSenderId(chat.getSenderId());
            messageDTO.setMessage(chat.getMessage());
            messageDTO.setChatType(ChatType.valueOf(chat.getChatType().name()));

            return messageDTO;
        }

//  채팅방&채팅 내용 검색

//  첨부 파일 업로드
}


