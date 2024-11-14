package com.threeping.syncday.schedule.query.config;

import com.threeping.syncday.schedule.query.repository.ScheduleMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@MapperScan(basePackages = "com.threeping.syncday.schedule.query.repository", annotationClass = Mapper.class)
@Configuration("ScheduleMybatisConfiguration")
public class MybatisConfig {
    private ScheduleMapper scheduleMapper;
}
