package com.threeping.syncday.cardboard.query.config;


import com.threeping.syncday.proj.query.repository.ProjMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@MapperScan(basePackages = "com.threeping.syncday.cardboard.query.repository")
@Configuration("CardBoardMybatisConfiguration")
public class MybatisConfig {
    private ProjMapper projMapper;
}
