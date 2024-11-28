package com.threeping.syncday.cardtag.query.config;

import com.threeping.syncday.cardtag.query.repository.CardTagMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@MapperScan("com.threeping.syncday.cardtag.query.repository")
@Configuration("CardTagMybatisConfig")
public class MybatisConfig {
    private CardTagMapper cardTagMapper;
}
