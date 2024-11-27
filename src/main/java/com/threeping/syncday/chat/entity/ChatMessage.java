package com.threeping.syncday.chat.entity;

import jakarta.persistence.Id;
import lombok.*;
import org.bson.types.ObjectId;
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
    private ObjectId messageId;
    private String content;
    private String roomId;
    private Long senderId;
    private String senderName;
    private int memberCount;
    private List<Long> memberIds;
    private List<String> memberNames;
    private ChatType chatType;

    @CreationTimestamp
    private LocalDateTime sentTime;


}
