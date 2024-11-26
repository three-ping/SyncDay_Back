package com.threeping.syncday.user.query.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserQueryDTO {
    private Long userId;
    private String name;
    private String email;
    private String profileImage;
    private String teamName;
    private String position;
}
