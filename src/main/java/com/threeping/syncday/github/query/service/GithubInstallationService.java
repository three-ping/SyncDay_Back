package com.threeping.syncday.github.query.service;

import com.threeping.syncday.github.query.aggregate.dto.GithubInstallationDTO;
import org.kohsuke.github.GHProject;

import java.io.IOException;
import java.util.List;

public interface GithubInstallationService {
    List<GithubInstallationDTO> getInstallationsByUserId(Long userId);

}
