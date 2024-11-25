package com.threeping.syncday.user.query.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserSearchResponse {
    private Long userId;
    private String name;
    private String email;
    private String profileImage;
    private String teamName;
    private String position;
}
