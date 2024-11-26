package com.threeping.syncday.user.command.application.dto;

import com.threeping.syncday.user.query.dto.UserQueryDTO;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserEventDTO {
    private Long userId;
    private String name;
    private String email;
    private String profileImage;
    private String teamName;
    private String position;

    public static UserEventDTO from(UserQueryDTO queryDTO) {
        return UserEventDTO.builder()
                .userId(queryDTO.getUserId())
                .name(queryDTO.getName())
                .email(queryDTO.getEmail())
                .profileImage(queryDTO.getProfileImage())
                .teamName(queryDTO.getTeamName())
                .position(queryDTO.getPosition())
                .build();
    }
}
