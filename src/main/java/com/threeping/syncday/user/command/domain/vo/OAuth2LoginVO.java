package com.threeping.syncday.user.command.domain.vo;

import lombok.Value;

@Value
public class OAuth2LoginVO {
    private String oauth2Id;
    private String oauth2AccessToken;
}
