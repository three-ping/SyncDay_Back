package com.threeping.syncday.github.command.application.service;


import com.threeping.syncday.github.command.aggregate.payload.GithubInstallationRequest;
import com.threeping.syncday.github.command.aggregate.payload.GithubInstallationResponse;

public interface AppGithubInstallationService {
    GithubInstallationResponse processInstallationAuth(GithubInstallationRequest githubInstallationRequest);
}
