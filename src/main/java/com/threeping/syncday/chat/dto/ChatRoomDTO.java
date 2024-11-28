package com.threeping.syncday.chat.dto;

import com.threeping.syncday.chat.entity.ChatRoom;
import com.threeping.syncday.user.command.application.service.UserCommandService;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
public class ChatRoomDTO {
    private String roomId;
    private String chatRoomName;
    private int membersCount;
    private List<Long> memberIds;

    public static ChatRoomDTO from(ChatRoom chatRoom, Map<Long, String> userNameMap) {
        return ChatRoomDTO.builder()
                .roomId(chatRoom.getRoomId())
                .chatRoomName(chatRoom.getChatRoomName(userNameMap))
                .membersCount(chatRoom.getMembersCount())
                .memberIds(chatRoom.getMemberIds())
                .build();
    }
}
