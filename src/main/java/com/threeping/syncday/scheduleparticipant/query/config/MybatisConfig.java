package com.threeping.syncday.scheduleparticipant.query.config;

import com.threeping.syncday.scheduleparticipant.query.repository.ScheduleParticipantMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@MapperScan(basePackages = "com.threeping.syncday.scheduleparticipant.query.repository", annotationClass = Mapper.class)
@Configuration("ScheduleParticipantMybatisConfiguration")
public class MybatisConfig {
    private ScheduleParticipantMapper scheduleParticipantMapper;
}
