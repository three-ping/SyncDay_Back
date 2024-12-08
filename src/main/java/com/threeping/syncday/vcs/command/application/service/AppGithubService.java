package com.threeping.syncday.vcs.command.application.service;

import com.threeping.syncday.vcs.command.aggregate.request.GithubInstallationRequest;
import com.threeping.syncday.vcs.command.aggregate.response.GithubInstallationResponse;

public interface AppGithubService {
    GithubInstallationResponse handleInstallation(GithubInstallationRequest req);
}
