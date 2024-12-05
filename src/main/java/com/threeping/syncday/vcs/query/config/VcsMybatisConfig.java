package com.threeping.syncday.vcs.query.config;

import com.threeping.syncday.vcs.query.repository.VcsOrgMapper;
import com.threeping.syncday.vcs.query.repository.VcsUserMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@MapperScan(basePackages = "com.threeping.syncday.vcs.query.repository", annotationClass = Mapper.class)
@Configuration
public class VcsMybatisConfig {

    private VcsOrgMapper vcsOrgMapper;
    private VcsUserMapper vcsUserMapper;
}
