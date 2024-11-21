package com.threeping.syncday.chat.entity;

import com.threeping.syncday.chat.dto.ChatMessageDTO;
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
@Builder
public class ChatMessage {

    @Id
    private String messageId;
    private String message;
    private String roomId;
    private String senderId;
    private String receiverId;
    private int memberCount;
    private List<String> memberIds;
    private ChatMessageDTO.ChatType chatType;

    @CreationTimestamp
    private LocalDateTime sentTime;


}
