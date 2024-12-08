package com.threeping.syncday.github.query.repository;

import com.threeping.syncday.github.query.aggregate.dto.GithubRepositoryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GithubRepositoryMapper {
List<GithubRepositoryDTO> selectGithubRepositoryByInstallationId(Long installationId);
}
