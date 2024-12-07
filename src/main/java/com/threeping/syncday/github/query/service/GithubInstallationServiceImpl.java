package com.threeping.syncday.github.query.service;

import com.threeping.syncday.github.infrastructure.github.GithubAppClient;
import com.threeping.syncday.github.query.aggregate.dto.GithubInstallationDTO;
import com.threeping.syncday.github.query.repository.GithubInstallationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kohsuke.github.GHProject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class GithubInstallationServiceImpl implements GithubInstallationService {

    private final GithubInstallationMapper githubInstallationMapper;
    private final GithubAppClient githubAppClient;
    @Override
    public List<GithubInstallationDTO> getInstallationsByUserId(Long userId) {
        return githubInstallationMapper.selectByUserId(userId);
    }


}
