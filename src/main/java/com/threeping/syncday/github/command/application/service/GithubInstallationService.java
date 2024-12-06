package com.threeping.syncday.github.command.application.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.threeping.syncday.github.command.aggregate.vo.InstallationAuthRequest;

public interface GithubInstallationService {
    Boolean processInstallationAuth(InstallationAuthRequest githubInstallationRequest) throws JsonProcessingException;
}
