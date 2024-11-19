package com.threeping.syncday.chat.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChatRoomDTO {
    private String roomId;
    private String chatRoomName;
    private String creatorId;
    private int memberCount;
    private String createdAt;
}