package com.threeping.syncday.chat.config;

import com.mongodb.client.MongoClient;
import com.threeping.syncday.chat.dto.ChatMessageDTO;
import com.threeping.syncday.chat.dto.ChatRoomDTO;
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
            // 기존 데이터 전체 삭제
            chatMessageRepository.deleteAll();
            chatRoomRepository.deleteAll();

            // 채팅방 더미 데이터 생성
            ChatRoom room1 = createChatRoom(
                    List.of(1L, 2L, 11L),
                    "이코딩, 김개발, 장그래"
            );

            ChatRoom room2 = createChatRoom(
                    List.of(3L, 11L),
                    "박디자인, 장그래"
            );

            ChatRoom room3 = createChatRoom(
                    List.of(1L, 4L, 5L),
                    "최마케팅, 김개발, 정그래픽"
            );

            // 채팅방 저장
            chatRoomRepository.saveAll(List.of(room1, room2, room3));

            // 채팅 메시지 더미 데이터 생성
            List<ChatMessage> messages = List.of(
                    createChatMessage("안녕하세요!", room1.getRoomId(), 2L, ChatType.GROUP),
                    createChatMessage("프로젝트 관련해서 이야기 나눠요.", room2.getRoomId(), 3L, ChatType.PRIVATE),
                    createChatMessage("안녕하세요, 함께 이야기해요!", room3.getRoomId(), 5L, ChatType.GROUP),
                    createChatMessage("일정에 대해 정리해두었습니다.", room3.getRoomId(), 1L, ChatType.GROUP),
                    createChatMessage("질문이 있습니다.", room1.getRoomId(), 1L, ChatType.PRIVATE)
            );

            // 채팅 메시지 저장
            chatMessageRepository.saveAll(messages);
        };
    }

    // ChatRoom 엔티티 생성 함수
    private ChatRoom createChatRoom(List<Long> memberIds, String chatRoomName) {
        return ChatRoom.builder()
                .roomId(UUID.randomUUID().toString())
                .memberIds(memberIds)
                .chatRoomName(chatRoomName)
                .build();
    }

    // ChatMessage 엔티티 생성 함수
    private ChatMessage createChatMessage(String content, String roomId, Long senderId, ChatType chatType) {
        return ChatMessage.builder()
                .content(content)
                .roomId(roomId)
                .senderId(senderId)
                .chatType(chatType)
                .sentTime(LocalDateTime.now())
                .build();
    }
}
