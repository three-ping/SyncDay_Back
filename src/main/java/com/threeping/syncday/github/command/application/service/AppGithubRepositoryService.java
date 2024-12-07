package com.threeping.syncday.github.command.application.service;

import org.kohsuke.github.GHAppInstallation;
import org.kohsuke.github.GHRepository;

import java.util.List;

public interface AppGithubRepositoryService {
    Boolean saveInstallationRepositories(Long installationId, GHAppInstallation appInstallation);

}
