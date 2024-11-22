package com.threeping.syncday.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@OpenAPIDefinition(info = @Info(title="SyncDay API 명세서",
                                description = "SyncDay API 명세서",
                                version = "v1"))
@Configuration
public class SwaggerConfiguration {

    @Bean
    @Profile("!Prod") // 운영환경은 제외
    public GroupedOpenApi userApi() {

        String[] paths = {"/api/user/**"};

        return GroupedOpenApi
                .builder()
                .group("유저 관련 api")
                .pathsToMatch(paths)
                .addOpenApiCustomizer(buildSecurityOpenApi()).build();
    }

    @Bean
    @Profile("!Prod")
    public GroupedOpenApi authApi() {
        String[] paths = {"/api/docs/**", "/api/user/login/**"};

        return GroupedOpenApi
                .builder()
                .group("인증 관련 api")
                .pathsToMatch(paths)
                .addOpenApiCustomizer(buildSecurityOpenApi())
                .build();
    }

    @Bean
    @Profile("!Prod")
    public GroupedOpenApi teamApi() {

        String[] paths = {"/api/team/**"};

        return GroupedOpenApi
                .builder()
                .group("팀 관련 api")
                .pathsToMatch(paths)
                .addOpenApiCustomizer(buildSecurityOpenApi()).build();
    }

    @Bean
    @Profile("!Prod")
    public GroupedOpenApi projectApi() {

        String[] paths = {"/api/proj/**"};

        return GroupedOpenApi
                .builder()
                .group("프로젝트 관련 api")
                .pathsToMatch(paths)
                .addOpenApiCustomizer(buildSecurityOpenApi()).build();
    }

    @Bean
    @Profile("!Prod")
    public GroupedOpenApi cardboardApi() {

        String[] paths = {"/api/cardboard/**"};

        return GroupedOpenApi
                .builder()
                .group("카드보드 관련 api")
                .pathsToMatch(paths)
                .addOpenApiCustomizer(buildSecurityOpenApi()).build();
    }

//    @Bean
//    @Profile("!Prod")
//    public GroupedOpenApi chatApi() {
//
//        String[] paths = {"/api/chat/**"};
//
//        return GroupedOpenApi
//                .builder()
//                .group("채팅 관련 api")
//                .pathsToMatch(paths)
//                .addOpenApiCustomizer(buildSecurityOpenApi()).build();
//    }

    @Bean
    @Profile("!Prod")
    public GroupedOpenApi cardApi() {

        String[] paths = {"/api/card/**"};

        return GroupedOpenApi
                .builder()
                .group("카드 관련 api")
                .pathsToMatch(paths)
                .addOpenApiCustomizer(buildSecurityOpenApi()).build();
    }

    @Bean
    @Profile("!Prod")
    public GroupedOpenApi calendarApi() {

        String[] paths = {"/api/calendar/**"};

        return GroupedOpenApi
                .builder()
                .group("일정 관련 api")
                .pathsToMatch(paths)
                .addOpenApiCustomizer(buildSecurityOpenApi()).build();
    }


    public OpenApiCustomizer buildSecurityOpenApi() {
        // jwt token을 한번 설정하면 header에 값을 넣어주는 코드
        return OpenApi -> OpenApi.addSecurityItem(new SecurityRequirement().addList("jwt token"))
                .getComponents().addSecuritySchemes("jwt token", new SecurityScheme()
                        .name("Authorization")
                        .type(SecurityScheme.Type.HTTP)
                        .in(SecurityScheme.In.HEADER)
                        .bearerFormat("JWT")
                        .scheme("bearer"));
    }
}
