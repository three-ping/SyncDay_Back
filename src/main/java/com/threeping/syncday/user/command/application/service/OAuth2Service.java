package com.threeping.syncday.user.command.application.service;



import com.threeping.syncday.user.aggregate.oauth.vo.GithubTokenResponse;

import java.util.Map;

public interface OAuth2Service {

    GithubTokenResponse getGithubAccessToken(String code);

    GithubTokenResponse refreshAccessToken(String refreshToken);
}