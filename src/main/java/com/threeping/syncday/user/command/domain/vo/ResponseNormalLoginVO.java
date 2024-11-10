package com.threeping.syncday.user.command.domain.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ResponseNormalLoginVO {
    @JsonProperty("access_token")
    private String accessToken;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @JsonProperty("access_token_expiry")
    private Date accessTokenExpiry;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @JsonProperty("refresh_token_expiry")
    private Date refreshTokenExpiry;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("username")
    private String userName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("profilephoto")
    private String profilePhoto;

    @JsonProperty("joinyear")
    private String joinYear;

    @JsonProperty("position")
    private String position;

    @JsonProperty("teamid")
    private Long teamId;

}
