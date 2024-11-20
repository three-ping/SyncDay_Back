package com.threeping.syncday.meetingroom.query.config;

import com.threeping.syncday.meetingroom.query.repository.MeetingroomMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@MapperScan(basePackages = "com.threeping.syncday.meetingroom.query.repository", annotationClass = Mapper.class)
@Configuration("MeetingroomMybatisConfiguration")
public class MybatisConfig {
    private MeetingroomMapper meetingroomMapper;
}
