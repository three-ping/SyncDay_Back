package com.threeping.syncday.chat.application;

import com.threeping.syncday.chat.dto.ChatMessageDTO;
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
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ChatService {
    private static final Logger log = LoggerFactory.getLogger(ChatService.class);
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final UserRepository userRepository;
    private final SimpMessagingTemplate messagingTemplate;


    @Autowired
    public ChatService(ChatRoomRepository chatRoomRepository,
                       ChatMessageRepository chatMessageRepository,
                       UserRepository userRepository,
                       SimpMessagingTemplate messagingTemplate) {
        this.chatRoomRepository = chatRoomRepository;
        this.chatMessageRepository = chatMessageRepository;
        this.userRepository = userRepository;
        this.messagingTemplate = messagingTemplate;

    }

    //  채팅방 목록 조회
    public List<ChatRoom> findUserChat(Long userId) {
        log.info("유저 채팅 목록 찾는 서비스 메서드 시작");

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    log.info("없는 유저입니다: {}", userId);
                    return new IllegalArgumentException("유저를 찾을 수 없습니다.: " + userId);
                });

        log.debug("해당 유저: {}", userId);

        List<ChatRoom> chatRoomList = chatRoomRepository.findChatRoomsByMemberIdsContaining(userId);
        log.info("채팅방 리스트 엔티티: {}", chatRoomList);

        return chatRoomList;
    }

    //  특정 채팅방 조회
    public List<ChatMessageDTO> findChatRoomByRoomId(String roomId, Long userId) {
        log.info("유저{}: {} 채팅방 메세지 조회 ", userId, roomId);
        Optional<ChatMessage> lastLeave = chatMessageRepository
                .findTopByRoomIdAndSenderIdAndChatTypeOrderBySentTimeDesc(
                        roomId, userId, ChatType.LEAVE);

        if (lastLeave.isPresent()) {
            LocalDateTime leaveTime = lastLeave.get().getSentTime();
            return chatMessageRepository.findByRoomIdAndSentTimeAfterOrderBySentTimeAsc(roomId, leaveTime)
                    .stream()
                    .map(this::convertToChatMessage)
                    .collect(Collectors.toList());
        }
        return chatMessageRepository.findByRoomIdOrderBySentTimeAsc(roomId)
                .stream()
                .map(this::convertToChatMessage)
                .collect(Collectors.toList());
    }

    //  채팅방 생성(멤버 필터) : 수정 예정
    public ChatRoom createChatRoom(ChatRoom chatRoom) {
        log.info("새 채팅방 생성: {}", chatRoom);
        // 멤버 기반 이름 설정
//            List<String> memberIds = chatRoom.getMemberIds();
//            String chatRoomName = memberIds.size() == 2
//                    ? "1:1 채팅 - " + memberIds.get(0) + " & " + memberIds.get(1)
//                    : String.join(", ", memberIds);
//
//            // 1:1 채팅방 중복 방지
//            if (memberIds.size() == 2) {
//
//                ChatRoom existingRoom = chatRoomRepository.find1to1ChatRoom(memberIds.get(0), memberIds.get(1));
//                if (existingRoom != null) {
//                    return existingRoom; // 이미 존재하면 해당 방 반환
//                }


        return chatRoomRepository.save(chatRoom);

    }


    //  특정 채팅방 퇴장
    public ChatRoom leaveChatRoom(String roomId, Long userId) {
        log.info("유저 {} : {} 채팅방 퇴장", userId, roomId);
        ChatRoom chatRoom = chatRoomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("채팅방이 존재하지 않습니다."));

        List<Long> memberIds = new ArrayList<>(chatRoom.getMemberIds());
        memberIds.remove(userId);
        chatRoom.setMemberIds(memberIds);

        LocalDateTime leaveTime = LocalDateTime.now();

        ChatMessage leaveRoom = new ChatMessage();
        leaveRoom.setRoomId(roomId);
        leaveRoom.setSenderId(userId);
        leaveRoom.setChatType(ChatType.LEAVE);
        leaveRoom.setSentTime(leaveTime);
        chatMessageRepository.save(leaveRoom);

        ChatRoom updatedRoom = chatRoomRepository.save(chatRoom);

        messagingTemplate.convertAndSend("/topic/room/" + roomId + "/leave", updatedRoom);

        return updatedRoom;

    }

    private Map<Long, String> getUserNameMap(ChatRoom chatRoom) {
        return userRepository.findByUserIdIn(chatRoom.getMemberIds())
                .stream()
                .collect(Collectors.toMap(UserEntity::getUserId, UserEntity::getUserName));
    }


// 멤버 초대
//    public ChatRoom inviteUser(String roomId, List<Long> userIds ) {
//        ChatRoom chatRoom = chatRoomRepository.findById(roomId)
//                .orElseThrow(() -> new RuntimeException("해당 채팅방이 존재하지 않습니다."));
//
//        List<Long> memberIds = new ArrayList<>(chatRoom.getMemberIds());
//        memberIds.addAll(userIds);
//        chatRoom.setMemberIds(memberIds);
//
//        ChatRoom updatedRoom = chatRoomRepository.save(chatRoom);
//
//        // 초대된 사용자들의 입장 메시지 생성 및 저장
//        for (Long userId : userIds) {
//            UserEntity user = userRepository.findByUserId(userId)
//                    .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
//
//            ChatMessage enterMessage = new ChatMessage();
//            enterMessage.setRoomId(roomId);
//            enterMessage.setSenderId(userId);
//            enterMessage.setContent(user.getUserName() + "님이 입장하셨습니다.");
//            enterMessage.setChatType(ChatType.ENTER);
//            enterMessage.setSentTime(LocalDateTime.now());
//            ChatMessage savedMessage = chatMessageRepository.save(enterMessage);
//
//            // 입장 메시지를 WebSocket을 통해 전송
//            ChatMessageDTO chatMessage = convertToChatMessage(savedMessage);
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

//    // 채팅방 이름 수정(단체톡방 일 시)
//    public ChatRoom updateRoomName(String roomId, String newRoom) {
//        log.info("{}채팅방 이름 수정: {}", roomId, newRoom);
//        ChatRoom chatRoom = chatRoomRepository.findById(roomId)
//                .orElseThrow(() -> new IllegalArgumentException("채팅방이 존재하지 않습니다."));
//
//        chatRoom.setChatRoomName(newRoom);
//        ChatRoom updatedChatRoom = chatRoomRepository.save(chatRoom);
//
//        return ChatRoom(updatedChatRoom, getUserNameMaps(updatedChatRoom));
//    }

    private Map<Long, String> getUserNameMaps(List<ChatRoom> chatRoom) {
        List<Long> allMemberIds = chatRoom.stream()
                .flatMap(room -> room.getMemberIds().stream())
                .distinct()
                .collect(Collectors.toList());

        return userRepository.findByUserIdIn(allMemberIds)
                .stream()
                .collect(Collectors.toMap(UserEntity::getUserId, UserEntity::getUserName));
    }

    //  채팅방 채팅 작성
    public void createMessage(String roomId, ChatMessageDTO chatMessage) {
        log.info("{} 채팅방에 메세지 전송: {}", roomId, chatMessage);
        try {
            ChatMessage message = new ChatMessage();
            message.setRoomId(roomId);
            message.setSenderId(chatMessage.getSenderId());
            message.setContent(chatMessage.getContent());
            message.setSentTime(LocalDateTime.now());
            message.setChatType(ChatType.valueOf(chatMessage.getChatType().name()));
            chatMessageRepository.save(message);

            messagingTemplate.convertAndSend("/topic/room/" + roomId, chatMessage);
        } catch (Exception e) {
            log.error("메세지 전송 중 오류 발생: {}", e.getMessage(), e);
        }
    }


    // 메세지 db에 저장
// ChatMessageDTO로 변환하여 메세지 db에 저장
    private ChatMessageDTO convertToChatMessage(ChatMessage message) {

        ChatMessageDTO chatMessage = new ChatMessageDTO();
        chatMessage.setRoomId(message.getRoomId());
        chatMessage.setContent(message.getContent());
        chatMessage.setSenderId(message.getSenderId());
        chatMessage.setChatType(ChatType.valueOf(message.getChatType().name()));
        return chatMessage;
    }

    public String getChatRoomName(ChatRoom chatRoom, Map<Long, String> userNameMap) {
        List<String> memberNames = chatRoom.getMemberIds().stream()
                .limit(2)
                .map(userNameMap::get)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        String nameString = String.join(", ", memberNames);
        return chatRoom.getMemberIds().size() > 2
                ? nameString + " 외 " + (chatRoom.getMemberIds().size() - 2) + "명"
                : nameString;
    }

    public int getMembersCount(ChatRoom chatRoom) {
        return chatRoom.getMemberIds().size();
    }


//  채팅방&채팅 내용 검색

//  첨부 파일 업로드

}

