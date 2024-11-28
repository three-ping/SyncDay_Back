package com.threeping.syncday.chat.dto;

import com.threeping.syncday.chat.entity.ChatType;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class ChatMessageDTO {


    private ObjectId messageId;
    private String content;
    private String roomId;
    private Long senderId;
    private ChatType chatType;
    private LocalDateTime sentTime;

//    private FileUploadDTO file;

}
