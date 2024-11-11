package com.threeping.syncday.user.command.domain.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class RegistRequestVO {

    @JsonProperty("username")
    @NotNull
    private String userName;

    @JsonProperty("password")
    @NotNull
    private String password;

    @JsonProperty("email")
    @NotNull
    private String email;

    @JsonProperty("phonenumber")
    private String phoneNumber;

    @JsonProperty("profilephoto")
    private String profilePhoto;

    @JsonProperty("joinyear")
    private String joinYear;

    @JsonProperty("position")
    private String position;

    @JsonProperty("teamid")
    @NotNull
    private Long teamId;
}