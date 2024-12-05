package com.threeping.syncday.chat.entity;

import jakarta.persistence.Id;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.UUID;


@Document(collection = "chat")
@Getter
@Setter
@ToString
public class ChatMessage {

    @Id
    @Field("_id")
    private ObjectId messageId;
    private String content;
    private String roomId;
    private Long senderId;
    private String senderName;
    private ChatType chatType;

    @CreatedDate
    private LocalDateTime sentTime;
}
