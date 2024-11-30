package com.threeping.syncday.chat.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;
import lombok.*;
import org.bson.types.ObjectId;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

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
    private ChatType chatType;

    @CreatedDate
    private LocalDateTime sentTime;
}
