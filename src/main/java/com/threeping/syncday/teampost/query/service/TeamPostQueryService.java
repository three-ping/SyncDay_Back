package com.threeping.syncday.teampost.query.service;

import com.github.pagehelper.PageInfo;
import com.threeping.syncday.teampost.query.aggregate.dto.TeamPostDTO;

public interface TeamPostQueryService {
    PageInfo<TeamPostDTO> findTeamBoardPostPage(long teamBoardId, int page, int pageSize);

    TeamPostDTO findTeamPostDetail(Long teamBoardId, Long teamPostId);
}
