package com.threeping.syncday.user.command.application.service;



import java.util.Map;

public interface OAuth2Service {

    public String getGithubAccessToken(String code);
}