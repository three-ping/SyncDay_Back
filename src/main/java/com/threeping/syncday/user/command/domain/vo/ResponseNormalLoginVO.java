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
