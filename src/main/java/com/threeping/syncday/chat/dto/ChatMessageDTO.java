package com.threeping.syncday.chat.dto;

import com.threeping.syncday.chat.entity.ChatType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChatMessageDTO {

    private String content;
    private String roomId;
    private Long senderId;
    private String senderName;
    private ChatType chatType;
    private LocalDateTime sentTime;

//    private FileUploadDTO file;
}
