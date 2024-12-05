package com.threeping.syncday.githuborgmember.query.repository;

import com.threeping.syncday.githuborgmember.query.aggreagate.dto.GithubOrgMemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GithubOrgMemberMapper {
    List<GithubOrgMemberDTO> findByUserId(Long userId);

}
