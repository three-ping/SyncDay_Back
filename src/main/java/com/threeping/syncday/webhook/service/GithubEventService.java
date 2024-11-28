package com.threeping.syncday.webhook.service;


public interface GithubEventService {
    void handle(String eventType, String payload);
}
