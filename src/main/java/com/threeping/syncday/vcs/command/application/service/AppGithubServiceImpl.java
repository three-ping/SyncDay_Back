package com.threeping.syncday.vcs.command.application.service;

import com.threeping.syncday.vcs.command.aggregate.request.GithubInstallationRequest;
import com.threeping.syncday.vcs.command.aggregate.response.GithubInstallationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AppGithubServiceImpl implements AppGithubService {

    @Override
    public GithubInstallationResponse handleInstallation(GithubInstallationRequest req) {
        return null;
    }
}
