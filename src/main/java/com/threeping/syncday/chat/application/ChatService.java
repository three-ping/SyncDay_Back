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
import java.util.UUID;
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
    public List<ChatRoomDTO> findUserChat(Long userId) {
        log.info("유저 채팅 목록 찾는 서비스 메서드 시작");
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다.: " + userId));

        List<ChatRoomDTO> chatRoomList = chatRoomRepository.findChatRoomsByMemberIdsContaining(userId);
        log.info("채팅룸 리스트 엔티티: {}", chatRoomList);

        return chatRoomList;
    }

    //  특정 채팅방 조회
    public ChatRoomDTO findChatRoomByRoomId(String roomId) {
        ChatRoom chatRoom = chatRoomRepository.findChatRoomByRoomId(roomId)
                .orElseThrow(() -> new IllegalArgumentException("해당 채팅방이 없습니다: " + roomId));
        return convertToChatRoomDTO(chatRoom);
    }


    //  채팅방 생성(멤버 필터)
    public ChatRoomDTO createChatRoom(ChatRoomDTO chatRoomDTO) {
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
                .chatRoomName(chatRoomDTO.getChatRoomName())
                .createdAt(LocalDateTime.now())
                .memberCount(chatRoomDTO.getMemberIds().size())
                .memberIds(chatRoomDTO.getMemberIds())
                .build();
        return chatRoomRepository.save(chatRoomDTO);
    }


    //  특정 채팅방 퇴장
    public ChatRoomDTO leaveChatRoom(String roomId, Long userId) {
        ChatRoomDTO chatRoomDTO = chatRoomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("채팅방이 존재하지 않습니다."));

        List<Long> memberIds = new ArrayList<>(chatRoomDTO.getMemberIds());
        memberIds.remove(userId);
        chatRoomDTO.setMemberIds(memberIds);
        chatRoomDTO.setMemberCount(memberIds.size());


        LocalDateTime leaveTime = LocalDateTime.now();  // 채팅방 나간 시간

        ChatMessageDTO leaveMessage = new ChatMessageDTO();
        leaveMessage.setRoomId(roomId);
        leaveMessage.setSenderId(userId);
        leaveMessage.setChatType(ChatType.LEAVE);
        leaveMessage.setSentTime(LocalDateTime.now());
        chatMessageRepository.save(leaveMessage);

        if (memberIds.isEmpty()) {
            chatRoomRepository.delete(chatRoomDTO);
            return null;
        }

        return chatRoomRepository.save(chatRoomDTO);
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
    public ChatRoomDTO updateRoomName(String roomId, String newRoom) {
        ChatRoomDTO chatRoomDTO = chatRoomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("채팅방이 존재하지 않습니다."));

        chatRoomDTO.setChatRoomName(newRoom);
        return chatRoomRepository.save(chatRoomDTO);
    }


    //  채팅방 채팅 작성
    public ChatMessageDTO createMessage(String roomId, ChatMessageDTO chatMessageDTO) {
        ChatRoomDTO chatRoomDTO = chatRoomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("해당 채팅방이 존재하지 않습니다."));

        ChatMessageDTO chatMessage = new ChatMessageDTO();
        chatMessage.setRoomId(roomId);
        chatMessage.setSenderId(chatMessageDTO.getSenderId());
        chatMessage.setSenderName(chatMessageDTO.getSenderName());
        chatMessage.setContent(chatMessageDTO.getContent());
        chatMessage.setChatType(chatMessageDTO.getChatType());
        chatMessage.setSentTime(LocalDateTime.now());

        return chatMessageRepository.save(chatMessageDTO);
    }

    // ChatRoomDTO로 전환하여 메세지 db에 저장
    private ChatRoomDTO convertToChatRoomDTO(ChatRoom chatRoom) {
        List<String> memberNames = userRepository.findAllById(chatRoom.getMemberIds())
                .stream()
                .map(UserEntity::getUserName)
                .collect(Collectors.toList());

        return ChatRoomDTO.builder()
                .roomId(chatRoom.getRoomId())
                .chatRoomName(chatRoom.getChatRoomName())
                .memberCount(chatRoom.getMemberCount())
                .memberIds(chatRoom.getMemberIds())
                .memberNames(memberNames)
                .createdAt(chatRoom.getCreatedAt())
                .build();
    }

    // ChatMessageDTO로 전환하여 메세지 db에 저장
    private ChatMessageDTO convertToChatMessageDTO(ChatMessage chatMessage) {
        ChatMessageDTO messageDTO = new ChatMessageDTO();
        messageDTO.setMessageId(chatMessage.getMessageId());
        messageDTO.setRoomId(chatMessage.getRoomId());
        messageDTO.setSenderId(chatMessage.getSenderId());
        messageDTO.setSenderName(chatMessage.getSenderName());
        messageDTO.setContent(chatMessage.getContent());
        messageDTO.setChatType(chatMessage.getChatType());
        messageDTO.setSentTime(chatMessage.getSentTime());
        return messageDTO;
    }

//  채팅방&채팅 내용 검색

//  첨부 파일 업로드
}


