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
public class ChatRoom {
    @Id
    private String roomId;
    private List<Long> memberIds;
    private String chatRoomName;

}
