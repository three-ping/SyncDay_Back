package com.threeping.syncday.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.threeping.syncday", annotationClass = Mapper.class)
public class MybatisConfiguration {
}
