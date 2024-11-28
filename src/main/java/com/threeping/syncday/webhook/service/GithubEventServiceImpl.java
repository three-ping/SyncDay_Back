package com.threeping.syncday.webhook.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class GithubEventServiceImpl implements GithubEventService {

    @Override
    public void handle(String eventType, String payload) {
        log.info("eventType: {}", eventType);
        log.info("payload: {}", payload);
    }
}
