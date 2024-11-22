package com.threeping.syncday.chat.config;

import com.mongodb.client.MongoClient;
import com.threeping.syncday.chat.entity.ChatMessage;
import com.threeping.syncday.chat.entity.ChatRoom;
import com.threeping.syncday.chat.entity.ChatType;
import com.threeping.syncday.chat.repository.ChatMessageRepository;
import com.threeping.syncday.chat.repository.ChatRoomRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Configuration
public class MongoConfig {
    @Bean
    public MongoTemplate mongoTemplate(MongoClient mongoClient) {
        return new MongoTemplate(mongoClient, "chatdb");
    }

    @Bean
    CommandLineRunner init(ChatRoomRepository chatRoomRepository, ChatMessageRepository chatMessageRepository) {
        return args -> {
            chatMessageRepository.deleteAll();
            chatRoomRepository.deleteAll();

            // 사용자 목록
            List<String> users = List.of("김개발", "이코딩", "박디자인", "정그래픽", "최마케팅");

            // 채팅방 더미 데이터 생성
            ChatRoom room1 = ChatRoom.builder()
                    .roomId("room1")
                    .chatRoomName(String.join(", ", "이코딩", "김개발"))
                    .creatorId("이코딩")
                    .memberIds(List.of("이코딩", "김개발"))
                    .memberCount(2)
                    .createdAt(LocalDateTime.now())
                    .build();

            ChatRoom room2 = ChatRoom.builder()
                    .roomId("room2")
                    .chatRoomName(String.join(", ", "박디자인", "정그래픽"))
                    .creatorId("박디자인")
                    .memberIds(List.of("박디자인", "정그래픽"))
                    .memberCount(2)
                    .createdAt(LocalDateTime.now())
                    .build();

            ChatRoom room3 = ChatRoom.builder()
                    .roomId("room3")
                    .chatRoomName(String.join(", ", "최마케팅", "김개발", "정그래픽"))
                    .creatorId("최마케팅")
                    .memberIds(List.of("최마케팅", "김개발", "정그래픽"))
                    .memberCount(3)
                    .createdAt(LocalDateTime.now())
                    .build();

            // 채팅방 저장
            chatRoomRepository.saveAll(List.of(room1, room2, room3));

            // 채팅 메시지 더미 데이터 생성
            ChatMessage message1 = ChatMessage.builder()
                    .messageId("msg1")
                    .message("안녕하세요!")
                    .roomId("room1")
                    .senderId("이코딩")
                    .receiverId("김개발")
                    .memberIds(List.of("이코딩", "김개발"))
                    .chatType(ChatType.PRIVATE) // 개인 채팅
                    .sentTime(LocalDateTime.now())
                    .build();

            ChatMessage message2 = ChatMessage.builder()
                    .messageId("msg2")
                    .message("프로젝트 관련해서 이야기 나눠요.")
                    .roomId("room2")
                    .senderId("박디자인")
                    .receiverId("정그래픽")
                    .memberIds(List.of("박디자인", "정그래픽"))
                    .chatType(ChatType.PRIVATE)
                    .sentTime(LocalDateTime.now())
                    .build();

            ChatMessage message3 = ChatMessage.builder()
                    .messageId("msg3")
                    .message("안녕하세요, 함께 이야기해요!")
                    .roomId("room3")
                    .senderId("최마케팅")
                    .receiverId(null) // 그룹 채팅의 경우 null
                    .memberIds(List.of("최마케팅", "김개발", "정그래픽"))
                    .chatType(ChatType.GROUP) // 그룹 채팅
                    .sentTime(LocalDateTime.now())
                    .build();

            ChatMessage message4 = ChatMessage.builder()
                    .messageId("msg4")
                    .message("일정에 대해 정리해두었습니다.")
                    .roomId("room3")
                    .senderId("김개발")
                    .receiverId(null)
                    .memberIds(List.of("최마케팅", "김개발", "정그래픽"))
                    .chatType(ChatType.GROUP)
                    .sentTime(LocalDateTime.now())
                    .build();

            ChatMessage message5 = ChatMessage.builder()
                    .messageId("msg5")
                    .message("질문이 있습니다.")
                    .roomId("room1")
                    .senderId("김개발")
                    .receiverId("이코딩")
                    .memberIds(List.of("이코딩", "김개발"))
                    .chatType(ChatType.PRIVATE)
                    .sentTime(LocalDateTime.now())
                    .build();

            // 채팅 메시지 저장
            chatMessageRepository.saveAll(List.of(message1, message2, message3, message4, message5));
        };    }

    // ChatRoom 생성 함수
    private ChatRoom createChatRoom(String creatorId, List<String> memberIds) {
        return ChatRoom.builder()
                .roomId(UUID.randomUUID().toString()) // 고유한 Room ID 생성
                .chatRoomName(String.join(", ", memberIds)) // 채팅방 이름 설정
                .creatorId(creatorId)
                .memberCount(memberIds.size())
                .memberIds(memberIds)
                .createdAt(LocalDateTime.now())
                .build();
    }

    private ChatMessage createChatMessage(String message, String roomId,
                                          String senderId, String receiverId,
                                          List<String> memberIds, ChatType chatType) {
        return ChatMessage.builder()
                .messageId(UUID.randomUUID().toString())
                .message(message)
                .roomId(roomId)
                .senderId(senderId)
                .receiverId(receiverId)
                .memberIds(memberIds)
                .chatType(chatType)
                .sentTime(LocalDateTime.now())
                .build();
    }

}
