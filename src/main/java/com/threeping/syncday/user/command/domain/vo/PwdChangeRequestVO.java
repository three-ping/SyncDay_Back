package com.threeping.syncday.user.command.domain.vo;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PwdChangeRequestVO {

    private String currentPwd;

    private String newPwd;

}
