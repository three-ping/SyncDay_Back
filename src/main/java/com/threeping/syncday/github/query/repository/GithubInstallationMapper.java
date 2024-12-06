package com.threeping.syncday.github.query.repository;

import com.threeping.syncday.github.query.aggregate.dto.GithubInstallationDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GithubInstallationMapper {
    List<GithubInstallationDTO> selectByUserId(Long userId);
}
