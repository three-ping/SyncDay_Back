package com.threeping.syncday.schedulerepeat.query.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.threeping.syncday.schedulerepeat",annotationClass = Mapper.class)
public class MybatisConfig {
}

