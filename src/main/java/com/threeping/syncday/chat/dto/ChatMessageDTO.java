package com.threeping.syncday.chat.dto;

import com.threeping.syncday.chat.entity.ChatType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ChatMessageDTO {

    private String messageId;
    private String message;
    private String roomId;
    private String senderId;
    private String receiverId;
    private int memberCount;
    private List<String> memberIds;
    private ChatType chatType;
    private LocalDateTime sentTime;

    private FileUploadDTO file;

}
