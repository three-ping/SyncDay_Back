package com.threeping.syncday.chat.entity;

import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "chat")
@Getter
@Setter
@ToString
public class ChatMessage {

    @Id
    private String messageId;
    private String message;
    private String roomId;
    private Long senderId;
    private Long receiverId;
    private int memberCount;
    private List<Long> memberIds;
    private ChatType chatType;

    @CreationTimestamp
    private LocalDateTime sentTime;


}
