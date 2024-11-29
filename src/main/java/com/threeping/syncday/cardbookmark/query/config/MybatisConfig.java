package com.threeping.syncday.cardbookmark.query.config;

import com.threeping.syncday.cardbookmark.query.repository.CardBookmarkMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@MapperScan("com.threeping.syncday.cardbookmark.query.repository")
@Configuration("CardBookmarkMybatisConfig")
public class MybatisConfig {
    CardBookmarkMapper cardBookmarkMapper;
}
