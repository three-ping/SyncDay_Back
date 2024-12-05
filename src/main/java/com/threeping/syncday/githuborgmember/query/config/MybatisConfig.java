package com.threeping.syncday.githuborgmember.query.config;

import com.threeping.syncday.githuborgmember.query.repository.GithubOrgMemberMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
@MapperScan(basePackages = "com.threeping.syncday.githuborgmember.query.repository", annotationClass = Mapper.class)
@Configuration
public class MybatisConfig {
    private GithubOrgMemberMapper githubOrgMemberMapper;
}
