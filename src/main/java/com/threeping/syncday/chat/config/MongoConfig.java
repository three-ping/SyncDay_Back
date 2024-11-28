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
            chatMessageRepository.deleteAll();
            chatRoomRepository.deleteAll();

            // 사용자 목록
            List<String> users = List.of("김개발", "이코딩", "박디자인", "정그래픽", "최마케팅", "장그래");

            // 채팅방 더미 데이터 생성
            ChatRoomDTO room1 = ChatRoomDTO.builder()
                    .roomId("room1")
                    .chatRoomName(String.join(", ", "이코딩", "김개발", "장그래"))
                    .memberIds(List.of(1L, 2L, 11L))
                    .build();

            ChatRoomDTO room2 = ChatRoomDTO.builder()
                    .roomId("room2")
                    .chatRoomName(String.join(", ", "박디자인", "장그래"))
                    .memberIds(List.of(3L, 11L))
                    .build();

            ChatRoomDTO room3 = ChatRoomDTO.builder()
                    .roomId("room3")
                    .chatRoomName(String.join(", ", "최마케팅", "김개발", "정그래픽"))
                    .memberIds(List.of(1L, 4L, 5L))
                    .build();

            // 채팅방 저장
            chatRoomRepository.saveAll(List.of(room1, room2, room3));

            // 채팅 메시지 더미 데이터 생성
            ChatMessageDTO message1 = new ChatMessageDTO();
            message1.setContent("안녕하세요!");
            message1.setRoomId("room1");
            message1.setSenderId(2L);
            message1.setChatType(ChatType.GROUP);
            message1.setSentTime(LocalDateTime.now());

            ChatMessageDTO message2 = new ChatMessageDTO();
            message2.setContent("프로젝트 관련해서 이야기 나눠요.");
            message2.setRoomId("room2");
            message2.setSenderId(3L);
            message2.setChatType(ChatType.PRIVATE);
            message2.setSentTime(LocalDateTime.now());

            ChatMessageDTO message3 = new ChatMessageDTO();
            message3.setContent("안녕하세요, 함께 이야기해요!");
            message3.setRoomId("room3");
            message3.setSenderId(5L);
            message3.setChatType(ChatType.GROUP);
            message3.setSentTime(LocalDateTime.now());

            ChatMessageDTO message4 = new ChatMessageDTO();
            message4.setContent("일정에 대해 정리해두었습니다.");
            message4.setRoomId("room3");
            message4.setSenderId(1L);
            message4.setChatType(ChatType.GROUP);
            message4.setSentTime(LocalDateTime.now());

            ChatMessageDTO message5 = new ChatMessageDTO();
            message5.setContent("질문이 있습니다.");
            message5.setRoomId("room1");
            message5.setSenderId(1L);
            message5.setChatType(ChatType.PRIVATE);
            message5.setSentTime(LocalDateTime.now());

            // 채팅 메시지 저장
            chatMessageRepository.saveAll(List.of(message1, message2, message3, message4, message5));
        };
    }

    // ChatRoomDTO 생성 함수
    private ChatRoomDTO createChatRoom(List<Long> memberIds, List<String> memberNames) {
        return ChatRoomDTO.builder()
                .roomId(UUID.randomUUID().toString())
                .chatRoomName("채팅방입니다.")
                .memberIds(memberIds)
                .build();
    }

    // ChatMessageDTO 생성 함수
    private ChatMessageDTO createChatMessage(String message, String roomId, Long senderId, String senderName, ChatType chatType) {
        ChatMessageDTO chatMessage = new ChatMessageDTO();
        chatMessage.setContent(message);
        chatMessage.setRoomId(roomId);
        chatMessage.setSenderId(senderId);
        chatMessage.setChatType(chatType);
        chatMessage.setSentTime(LocalDateTime.now());
        return chatMessage;
    }
}