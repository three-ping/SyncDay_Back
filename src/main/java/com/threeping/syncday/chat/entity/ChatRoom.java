package com.threeping.syncday.chat.entity;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "chatroom")
@Getter
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
