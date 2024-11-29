package com.threeping.syncday.cardcomment.query.config;

import com.threeping.syncday.cardcomment.query.repository.CardCommentMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@MapperScan("com.threeping.syncday.cardcomment.query.repository")
@Configuration("CardCommentMybatisConfig")
public class MybatisConfig {
    private CardCommentMapper cardCommentMapper;
}
