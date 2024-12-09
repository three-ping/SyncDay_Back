package com.threeping.syncday.github.query.service;

import com.threeping.syncday.github.query.aggregate.dto.GithubMilestoneDTO;

import java.util.List;

public interface GithubMilestoneService {
    List<GithubMilestoneDTO> getMilestones(Long repoId);
}
