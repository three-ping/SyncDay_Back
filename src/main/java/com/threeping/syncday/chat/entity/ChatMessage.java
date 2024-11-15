package com.threeping.syncday.chat.entity;

import com.threeping.syncday.chat.dto.ChatMessageDTO;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "chat")
@Getter
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
    private String memberIds;
    private ChatType chatType;
    private String sentTime;


}
