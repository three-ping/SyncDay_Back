package com.threeping.syncday.github.query.service;

import com.threeping.syncday.github.command.aggregate.entity.GithubRepositoryEntity;
import com.threeping.syncday.github.query.aggregate.dto.GithubRepositoryDTO;
import com.threeping.syncday.github.query.repository.GithubRepositoryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GithubRepositoryServiceImpl implements GithubRepositoryService {

    private final GithubRepositoryMapper githubRepositoryMapper;
    @Override
    public List<GithubRepositoryDTO> getByInstallationId(Long installationId) {

        return githubRepositoryMapper.selectGithubRepositoryByInstallationId(installationId);
    }

    @Override
    public GithubRepositoryDTO getById(Long repoId) {
        return githubRepositoryMapper.selectGithubRepositoryById(repoId);
    }
}
