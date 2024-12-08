package com.threeping.syncday.vcs.query.service;

import com.threeping.syncday.vcs.query.aggregate.dto.VcsInstallationDTO;
import com.threeping.syncday.vcs.query.repository.VcsInstallationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class GithubServiceImpl implements GithubService {
private final VcsInstallationMapper vcsInstallationMapper;

    @Override
    public List<VcsInstallationDTO> getByUserId(Long userId) {
        return vcsInstallationMapper.selectByUserIdAndVcsType(userId, "GITHUB");
    }
}
