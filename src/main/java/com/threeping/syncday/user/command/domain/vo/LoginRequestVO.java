package com.threeping.syncday.user.command.domain.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table
@EqualsAndHashCode
public class LoginRequestVO {

    @JsonProperty("email")
    private String emil;

    @JsonProperty("password")
    private String password;
}
