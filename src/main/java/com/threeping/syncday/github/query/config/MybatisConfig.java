package com.threeping.syncday.github.query.config;

import com.threeping.syncday.github.query.repository.GithubInstallationMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@MapperScan(basePackages = "com.threeping.syncday.github.query.repository", annotationClass = Mapper.class)
@Configuration("githubInstallationMybatisConfig")
public class MybatisConfig {
    private GithubInstallationMapper githubInstallationMapper;
}
