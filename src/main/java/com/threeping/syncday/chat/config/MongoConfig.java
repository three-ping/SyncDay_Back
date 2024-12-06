package com.threeping.syncday.chat.config;

import com.mongodb.client.MongoClient;

import com.threeping.syncday.chat.entity.ChatMessage;
import com.threeping.syncday.chat.entity.ChatRoom;
import com.threeping.syncday.chat.entity.ChatType;
import com.threeping.syncday.chat.repository.ChatMessageRepository;
import com.threeping.syncday.chat.repository.ChatRoomRepository;
import com.threeping.syncday.user.command.domain.aggregate.UserEntity;
import com.threeping.syncday.user.command.domain.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.time.LocalDateTime;
import java.util.*;


@Configuration
public class MongoConfig {

    @Bean
    public MongoTemplate mongoTemplate(MongoClient mongoClient) {
        return new MongoTemplate(mongoClient, "chatdb");
    }

    @Bean
    CommandLineRunner init(UserRepository userRepository,
                           ChatRoomRepository chatRoomRepository,
                           ChatMessageRepository chatMessageRepository) {
        return args -> {
            // MongoDB 기존 데이터 초기화
            chatRoomRepository.deleteAll();
            chatMessageRepository.deleteAll();

            // 유저 데이터 조회 (RDBMS에서)
            UserEntity user1 = userRepository.findByUserId(1L)
                    .orElseThrow(() -> new RuntimeException("User 1 not found"));
            UserEntity user2 = userRepository.findByUserId(2L)
                    .orElseThrow(() -> new RuntimeException("User 2 not found"));
            UserEntity user3 = userRepository.findByUserId(3L)
                    .orElseThrow(() -> new RuntimeException("User 3 not found"));
            UserEntity user4 = userRepository.findByUserId(4L)
                    .orElseThrow(() -> new RuntimeException("User 4 not found"));
            UserEntity user5 = userRepository.findByUserId(5L)
                    .orElseThrow(() -> new RuntimeException("User 5 not found"));
            UserEntity user6 = userRepository.findByUserId(6L)
                    .orElseThrow(() -> new RuntimeException("User 6 not found"));
            UserEntity user7 = userRepository.findByUserId(7L)
                    .orElseThrow(() -> new RuntimeException("User 7 not found"));
            UserEntity user8 = userRepository.findByUserId(8L)
                    .orElseThrow(() -> new RuntimeException("User 8 not found"));
            UserEntity user9 = userRepository.findByUserId(9L)
                    .orElseThrow(() -> new RuntimeException("User 9 not found"));
            UserEntity user10 = userRepository.findByUserId(10L)
                    .orElseThrow(() -> new RuntimeException("User 10 not found"));
//            UserEntity user11 = userRepository.findByUserId(11L)
//                    .orElseThrow(() -> new RuntimeException("User 11 not found"));

            // 채팅방 더미 데이터 생성
            ChatRoom room1 = createChatRoom(
                    String.join(",", user1.getUserName(), user10.getUserName()),
                    Arrays.asList(user1.getUserId(), user10.getUserId())
            );

            ChatRoom room2 = createChatRoom(
                    String.join(",", user2.getUserName(), user3.getUserName(), user10.getUserName(), user10.getUserName()),
                    Arrays.asList(user2.getUserId(), user3.getUserId(), user10.getUserId(), user10.getUserId())
            );

            ChatRoom room3 = createChatRoom(
                    String.join(",", user1.getUserName(), user6.getUserName(), user9.getUserName(), user10.getUserName()),
                    Arrays.asList(user1.getUserId(), user6.getUserId(), user9.getUserId(),user9.getUserId()));

            ChatRoom room4 = createChatRoom(
                    String.join(",", user8.getUserName(), user9.getUserName(), user10.getUserName()),
                    Arrays.asList(user8.getUserId(), user9.getUserId(), user10.getUserId())
            );

            ChatRoom room5 = createChatRoom(
                    String.join(",", user1.getUserName(), user10.getUserName(), user7.getUserName()),
                    Arrays.asList(user1.getUserId(), user10.getUserId(), user7.getUserId())
            );

            ChatRoom room6 = createChatRoom(
                    String.join(",", user2.getUserName(), user4.getUserName(), user6.getUserName()),
                    Arrays.asList(user2.getUserId(), user4.getUserId(), user6.getUserId())
            );

            ChatRoom room7 = createChatRoom(
                    String.join(",", user3.getUserName(), user5.getUserName(), user9.getUserName()),
                    Arrays.asList(user3.getUserId(), user5.getUserId(), user9.getUserId())
            );

            ChatRoom room8 = createChatRoom(
                    String.join(",", user10.getUserName(), user10.getUserName()),
                    Arrays.asList(user10.getUserId(), user10.getUserId())
            );

            ChatRoom room9 = createChatRoom(
                    String.join(",", user8.getUserName(), user2.getUserName(), user1.getUserName()),
                    Arrays.asList(user8.getUserId(), user2.getUserId(), user1.getUserId())
            );

            ChatRoom room10 = createChatRoom(
                    String.join(",", user6.getUserName(), user4.getUserName(), user3.getUserName(), user5.getUserName()),
                    Arrays.asList(user6.getUserId(), user4.getUserId(), user3.getUserId(), user5.getUserId())
            );


            chatRoomRepository.saveAll(Arrays.asList(room1, room2, room3));

            // MongoDB 채팅 메시지 더미 데이터 생성
// MongoDB 채팅 메시지 더미 데이터 생성
            List<ChatMessage> messages = Arrays.asList(
                    createChatMessage(room1.getRoomId(), user1.getUserId(), user1.getUserName(), "안녕하세요!",
                            LocalDateTime.now().minusHours(2), ChatType.TALK),
                    createChatMessage(room1.getRoomId(), user2.getUserId(), user2.getUserName(), "네, 안녕하세요!",
                            LocalDateTime.now().minusHours(1), ChatType.TALK),
                    createChatMessage(room1.getRoomId(), user1.getUserId(), user1.getUserName(), "오늘 날씨가 좋네요.",
                            LocalDateTime.now().minusMinutes(30), ChatType.TALK),

                    createChatMessage(room2.getRoomId(), user2.getUserId(), user2.getUserName(), "개발자 채팅방에 오신 것을 환영합니다!",
                            LocalDateTime.now().minusDays(1), ChatType.TALK),
                    createChatMessage(room2.getRoomId(), user3.getUserId(), user3.getUserName(), "반갑습니다~",
                            LocalDateTime.now().minusDays(1).plusHours(1), ChatType.TALK),
                    createChatMessage(room2.getRoomId(), user2.getUserId(), user2.getUserName(), "프로젝트 진행상황 공유해주세요.",
                            LocalDateTime.now().minusHours(5), ChatType.TALK)
            );

            chatMessageRepository.saveAll(messages);
        };
    }

    private ChatRoom createChatRoom(String name, List<Long> memberIds) {
        ChatRoom room = new ChatRoom();
        room.setRoomId(UUID.randomUUID().toString());
        room.setChatRoomName(name);
        room.setMemberIds(memberIds);
        return room;
    }

    private ChatMessage createChatMessage(String roomId, Long senderId, String senderName, String content, LocalDateTime sentTime, ChatType chatType) {
        ChatMessage message = new ChatMessage();
        message.setMessageId(ObjectId.get());
        message.setRoomId(roomId);
        message.setSenderId(senderId);
        message.setSenderName(senderName);
        message.setContent(content);
        message.setChatType(chatType);
        message.setSentTime(sentTime);
        return message;
    }
}

