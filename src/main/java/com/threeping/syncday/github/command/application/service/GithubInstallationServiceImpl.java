package com.threeping.syncday.github.command.application.service;


import com.threeping.syncday.github.command.domain.GithubInstallationRepository;
import com.threeping.syncday.github.command.infrastructure.github.GithubAppClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class GithubInstallationServiceImpl {

    private final GithubInstallationRepository installationRepository;
    private final GithubAppClient githubClient;

}