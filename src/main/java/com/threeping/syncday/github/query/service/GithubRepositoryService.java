package com.threeping.syncday.github.query.service;

import com.threeping.syncday.github.command.aggregate.entity.GithubRepositoryEntity;
import com.threeping.syncday.github.query.aggregate.dto.GithubRepositoryDTO;

import java.util.List;

public interface GithubRepositoryService {
    List<GithubRepositoryDTO> getByInstallationId(Long installationId);

    GithubRepositoryDTO getById(Long repoId);
}
