package com.threeping.syncday.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    // BcryptPasswordEncoder bean 등록
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // redis 통신용 restTemplate bean 등록
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
