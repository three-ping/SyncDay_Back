package com.threeping.syncday.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://syncday-front-env.eba-mgj2hqmj.ap-northeast-2.elasticbeanstalk.com","https://1bda-203-234-103-13.ngrok-free.app")
                        .allowedMethods("GET", "POST", "OPTIONS", "PUT", "DELETE")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
    }