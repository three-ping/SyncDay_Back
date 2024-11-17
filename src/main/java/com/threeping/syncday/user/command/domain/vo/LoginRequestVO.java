package com.threeping.syncday.user.command.domain.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class LoginRequestVO {

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;
}
