package com.threeping.syncday.chat.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ChatRoomDTO {
    private String roomId;
    private String chatRoomName;
    private Long creatorId;
    private int memberCount;
    private List<Long> memberIds;
    private LocalDateTime createdAt;
}
