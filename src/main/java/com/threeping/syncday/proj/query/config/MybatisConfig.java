package com.threeping.syncday.proj.query.config;

import com.threeping.syncday.proj.query.repository.ProjMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@MapperScan(basePackages = "com.threeping.syncday.proj.query.repository", annotationClass = Mapper.class)
@Configuration("ProjMybatisConfiguration")
public class MybatisConfig {
    private ProjMapper projMapper;
}
