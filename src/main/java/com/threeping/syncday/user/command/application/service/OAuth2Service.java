package com.threeping.syncday.user.command.application.service;


public interface OAuth2Service {

    String getGithubAccessToken(String code);
}
