package com.threeping.syncday.chat.entity;

import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "chatroom")
@Getter
@Setter
@ToString
@Builder
public class ChatRoom {
    @Id
    private String roomId;
    private String chatRoomName;
    private Long creatorId;
    private int memberCount;
    private List<String> memberNames;
    private List<Long> memberIds;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
