package com.threeping.syncday.user.query.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class UserSearchResponse {
    private Long userId;
    private String name;
    private String email;
    private String profileImage;
    private String teamName;
    private String position;
}
