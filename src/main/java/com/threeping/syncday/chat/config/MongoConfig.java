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

            // RDBMS에서 유저 데이터 로드
            List<UserEntity> users = userRepository.findAll();
            if (users.size() < 5) {
                throw new IllegalStateException("초기화를 위해 최소 5명의 유저 데이터가 필요합니다.");
            }

            // 유저 데이터 출력 (로깅 용도)
            users.forEach(user -> System.out.println("Loaded User: " + user));

            // MongoDB 채팅방 더미 데이터 생성
            ChatRoom room1 = createChatRoom("room1",
                    "팀 프로젝트 논의방",
                    users.subList(0, 3).stream().map(UserEntity::getUserId).toList());

            ChatRoom room2 = createChatRoom("room2",
                    "마케팅 아이디어 공유방",
                    users.subList(2, 5).stream().map(UserEntity::getUserId).toList());

            ChatRoom room3 = createChatRoom("room3",
                    "전체 공지방",
                    users.stream().map(UserEntity::getUserId).toList());

            chatRoomRepository.saveAll(List.of(room1, room2, room3));

            // MongoDB 채팅 메시지 더미 데이터 생성
            List<ChatMessage> messages = List.of(
                    createChatMessage(room1.getRoomId(), users.get(0).getUserId(), "안녕하세요! 회의 시작합니다.", ChatType.GROUP),
                    createChatMessage(room1.getRoomId(), users.get(1).getUserId(), "네, 준비 완료입니다.", ChatType.GROUP),
                    createChatMessage(room2.getRoomId(), users.get(3).getUserId(), "이번 아이디어는 어떠세요?", ChatType.GROUP),
                    createChatMessage(room2.getRoomId(), users.get(4).getUserId(), "좋은 생각입니다. 추가 자료를 준비하겠습니다.", ChatType.GROUP),
                    createChatMessage(room3.getRoomId(), users.get(2).getUserId(), "내일 전체 회의가 있습니다. 확인 부탁드립니다.", ChatType.GROUP)
            );

            chatMessageRepository.saveAll(messages);
        };
    }

    private ChatRoom createChatRoom(String roomId, String name, List<Long> memberIds) {
        ChatRoom room = new ChatRoom();
        room.setRoomId(roomId);
        room.setChatRoomName(name);
        room.setMemberIds(memberIds);
        return room;
    }

    private ChatMessage createChatMessage(String roomId, Long senderId, String content, ChatType chatType) {
        ChatMessage message = new ChatMessage();
        message.setMessageId(new ObjectId());
        message.setRoomId(roomId);
        message.setSenderId(senderId);
        message.setContent(content);
        message.setChatType(chatType);
        message.setSentTime(LocalDateTime.now());
        return message;
    }
}

