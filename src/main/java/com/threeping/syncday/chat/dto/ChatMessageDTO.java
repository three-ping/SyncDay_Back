package com.threeping.syncday.chat.dto;

import com.threeping.syncday.chat.entity.ChatType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChatMessageDTO {

    private String messageId;
    private String message;
    private String roomId;
    private String senderId;
    private String receiverId;
    private int memberCount;
    private String memberIds;
    private ChatType chatType;
    private String sentTime;

    private FileUploadDTO file;


}
