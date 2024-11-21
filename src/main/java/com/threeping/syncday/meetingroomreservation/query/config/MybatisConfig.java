package com.threeping.syncday.meetingroomreservation.query.config;

import com.threeping.syncday.meetingroomreservation.query.repository.MeetingroomReservationMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@MapperScan(basePackages = "com.threeping.syncday.meetingroomreservation.query.repository", annotationClass = Mapper.class)
@Configuration("MeetingroomReservationMybatisConfiguration")
public class MybatisConfig {
    private MeetingroomReservationMapper meetingroomReservationMapper;

}
