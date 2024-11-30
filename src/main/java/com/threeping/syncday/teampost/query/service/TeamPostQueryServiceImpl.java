package com.threeping.syncday.teampost.query.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.threeping.syncday.teampost.query.aggregate.dto.TeamPostDTO;
import com.threeping.syncday.teampost.query.repository.TeamPostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamPostQueryServiceImpl implements TeamPostQueryService{

    private final TeamPostMapper teamPostMapper;

    @Autowired
    public TeamPostQueryServiceImpl(TeamPostMapper teamPostMapper) {
        this.teamPostMapper = teamPostMapper;
    }

    @Override
    public PageInfo<TeamPostDTO> findTeamBoardPostPage(long teamBoardId, int page, int pageSize) {
        PageHelper.startPage(page,pageSize);

        List<TeamPostDTO> teamPostDTOS = teamPostMapper.findTeamPostByTeamBoardId(teamBoardId);

        return new PageInfo<>(teamPostDTOS);
    }
}
