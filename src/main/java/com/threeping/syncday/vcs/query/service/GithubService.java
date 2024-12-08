package com.threeping.syncday.vcs.query.service;

import com.threeping.syncday.vcs.query.aggregate.dto.VcsInstallationDTO;

import java.util.List;

public interface GithubService {
    List<VcsInstallationDTO> getByUserId(Long userId);
}
