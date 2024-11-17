package com.threeping.syncday.chat.entity;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "chatroom")
@Getter
@Setter
@ToString
@Builder
public class ChatRoom {
    @Id
    private String roomId;
    private String chatRoomName;
    private String creatorId;
    private int memberCount;
    private String createdAt;
}
