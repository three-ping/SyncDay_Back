package com.threeping.syncday.card.query.config;

import com.threeping.syncday.card.query.repository.CardMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@MapperScan("com.threeping.syncday.card.query.repository")
@Configuration("CardMybatisConfiguration")
public class MybatisConfig {
    private CardMapper cardMapper;
}
