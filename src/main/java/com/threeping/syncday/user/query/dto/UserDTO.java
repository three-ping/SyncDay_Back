package com.threeping.syncday.user.query.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {

    private Long userId;

    private String userName;

    private String email;

    private String password;

    private String phoneNumber;

    private String profilePhoto;

    private String joinYear;

    private String position;

    private Long teamId;
}
