package com.threeping.syncday.workspace.query.config;

import com.threeping.syncday.workspace.query.respository.WorkspaceMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@MapperScan(value = "com.threeping.syncday.workspace.query.respository", annotationClass = Mapper.class)
@Configuration("WorkspaceMybatisConfiguration")
public class MybatisConfig {
    private WorkspaceMapper workspaceMapper;
}
