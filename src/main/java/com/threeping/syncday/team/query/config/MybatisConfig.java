package com.threeping.syncday.team.query.config;

import com.threeping.syncday.team.query.repository.TeamMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@MapperScan(basePackages= "com.threeping.syncday.team.query.repository", annotationClass = Mapper.class)
@Configuration("TeamMybatisConfiguration")
public class MybatisConfig {
    private TeamMapper teamMapper;
}