package com.threeping.syncday.teampost.query.repository;

import com.threeping.syncday.teampost.query.aggregate.dto.TeamPostDTO;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface TeamPostMapper {
    List<TeamPostDTO> findTeamPostByTeamBoardId(long teamBoardId);
}
