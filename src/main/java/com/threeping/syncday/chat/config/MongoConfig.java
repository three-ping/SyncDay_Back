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
            List<String> users = List.of("김개발", "이코딩", "박디자인", "정그래픽", "최마케팅", "장그래");

            // 채팅방 더미 데이터 생성
            ChatRoom room1 = ChatRoom.builder()
                    .roomId("room1")
                    .chatRoomName(String.join(", ", "이코딩", "김개발","장그래"))
                    .creatorId(2L)
                    .memberIds(List.of(1L, 2L, 11L))
                    .memberCount(2)
                    .createdAt(LocalDateTime.now())
                    .build();

            ChatRoom room2 = ChatRoom.builder()
                    .roomId("room2")
                    .chatRoomName(String.join(", ", "박디자인", "장그래"))
                    .creatorId(3L)
                    .memberIds(List.of(3L, 11L))
                    .memberCount(2)
                    .createdAt(LocalDateTime.now())
                    .build();

            ChatRoom room3 = ChatRoom.builder()
                    .roomId("room3")
                    .chatRoomName(String.join(", ", "최마케팅", "김개발", "정그래픽"))
                    .creatorId(5L)
                    .memberIds(List.of(1L, 4L, 5L))
                    .memberCount(3)
                    .createdAt(LocalDateTime.now())
                    .build();

            // 채팅방 저장
            chatRoomRepository.saveAll(List.of(room1, room2, room3));

            // 채팅 메시지 더미 데이터 생성
            ChatMessage message1 = new ChatMessage();
            message1.setMessageId("msg1");
            message1.setMessage("안녕하세요!");
            message1.setRoomId("room1");
            message1.setSenderId(2L);
            message1.setMemberIds(List.of(2L,1L,11L));
            message1.setChatType(ChatType.GROUP);
            message1.setSentTime(LocalDateTime.now());

            ChatMessage message2 = new ChatMessage();
            message2.setMessageId("msg2");
            message2.setMessage("프로젝트 관련해서 이야기 나눠요.");
            message2.setRoomId("room2");
            message2.setSenderId(3L);
            message2.setMemberIds(List.of(3L, 4L));
            message2.setChatType(ChatType.PRIVATE);
            message2.setSentTime(LocalDateTime.now());

            ChatMessage message3 = new ChatMessage();
            message3.setMessageId("msg3");
            message3.setMessage("안녕하세요, 함께 이야기해요!");
            message3.setRoomId("room3");
            message3.setSenderId(5L);
            message3.setReceiverId(null); // 그룹 채팅의 경우 null
            message3.setMemberIds(List.of(5L,1L,4L));
            message3.setChatType(ChatType.GROUP); // 그룹 채팅
            message3.setSentTime(LocalDateTime.now());

            ChatMessage message4 = new ChatMessage();
            message4.setMessageId("msg4");
            message4.setMessage("일정에 대해 정리해두었습니다.");
            message4.setRoomId("room3");
            message4.setSenderId(1L);
            message4.setMemberIds(List.of(5L,1L,4L));
            message4.setChatType(ChatType.GROUP);
            message4.setSentTime(LocalDateTime.now());

            ChatMessage message5 = new ChatMessage();
            message5.setMessageId("msg5");
            message5.setMessage("질문이 있습니다.");
            message5.setRoomId("room1");
            message5.setSenderId(1L);
            message5.setMemberIds(List.of(2L,1L));
            message5.setChatType(ChatType.PRIVATE);
            message5.setSentTime(LocalDateTime.now());

            // 채팅 메시지 저장
            chatMessageRepository.saveAll(List.of(message1, message2, message3, message4, message5));
        };
    }

    // ChatRoom 생성 함수
    private ChatRoom createChatRoom(Long creatorId, List<Long> memberIds) {
        return ChatRoom.builder()
                .roomId(UUID.randomUUID().toString()) // 고유한 Room ID 생성
                .chatRoomName("채팅방입니다.") // 채팅방 이름 설정
                .creatorId(creatorId)
                .memberCount(memberIds.size())
                .memberIds(memberIds)
                .createdAt(LocalDateTime.now())
                .build();
    }

    // ChatMessage 생성 함수
    private ChatMessage createChatMessage(String message, String roomId, Long senderId, Long receiverId, List<Long> memberIds, ChatType chatType) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setMessageId(UUID.randomUUID().toString());
        chatMessage.setMessage(message);
        chatMessage.setRoomId(roomId);
        chatMessage.setSenderId(senderId);
        chatMessage.setMemberIds(memberIds);
        chatMessage.setChatType(chatType);
        chatMessage.setSentTime(LocalDateTime.now());
        return chatMessage;
    }

}
