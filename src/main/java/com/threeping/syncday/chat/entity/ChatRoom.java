package com.threeping.syncday.chat.entity;

import com.threeping.syncday.user.command.application.service.UserCommandService;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Document(collection = "chatroom")
@Getter
@Setter
@ToString
@Builder
public class ChatRoom {
    @Id
    private String roomId;
    private List<Long> memberIds;

    public String getChatRoomName(Map<Long, String> userNameMap) {
        List<String> memberNames = memberIds.stream()
                .limit(2)
                .map(userNameMap::get)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        String nameString = String.join(", ", memberNames);
        return memberIds.size() > 2
                ? nameString + " 외 " + (memberIds.size() - 2) + "명"
                : nameString;
    }

    public int getMembersCount() {
        return memberIds.size();
    }
}
