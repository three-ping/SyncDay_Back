package com.threeping.syncday.user.query.controller;

import com.threeping.syncday.common.ResponseDTO;
import com.threeping.syncday.user.command.application.dto.UserDTO;
import com.threeping.syncday.user.command.domain.vo.LoginRequestVO;
import com.threeping.syncday.user.command.domain.vo.ResponseNormalLoginVO;
import com.threeping.syncday.user.query.service.UserQueryService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/* 설명. Swagger API를 위한 로그인 전용 Controller입니다. */

@RestController
@RequestMapping("/api/docs")
@Tag(name = "Authentication", description = "인증 관련 API")
@Slf4j
public class AuthDocsController {

    private final AuthenticationManagerBuilder authenticationManager;
    private final Environment env;
    private final UserQueryService userService;

    public AuthDocsController(AuthenticationManagerBuilder authenticationManager, Environment environment, UserQueryService userService) {
        this.authenticationManager = authenticationManager;
        this.env = environment;
        this.userService = userService;
    }

    @Operation(
            summary = "로그인",
            description = "사용자 아이디와 비밀번호로 로그인하여 JWT Token을 발급받습니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "로그인 성공!",
                            content = @Content(schema = @Schema(implementation = ResponseNormalLoginVO.class))
                    )
            }
    )
    @PostMapping("/login")
    public ResponseDTO<?> login(@RequestBody LoginRequestVO loginRequestVO, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.info("Swagger API login request 들어옴: {}", loginRequestVO);

        UsernamePasswordAuthenticationToken authRequest =
                new UsernamePasswordAuthenticationToken(loginRequestVO.getEmail(),
                        loginRequestVO.getPassword(), new ArrayList<>());

        Authentication authentication =
                authenticationManager.getObject().authenticate(authRequest);

        String email = ((User) authentication.getPrincipal()).getUsername();
        Claims claims = Jwts.claims().setSubject(email);
        List<String> roles = authentication.getAuthorities().stream()
                .map(role -> role.getAuthority())
                .collect(Collectors.toList()); // 권한 정보
        claims.put("auth", roles);

        // 토큰 만료 시간 설정
        long accessExpiration =
                System.currentTimeMillis() + Long.parseLong(env.getProperty("token.access-expiration-time"));

        // AT 생성
        String accessToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + accessExpiration))
                .signWith(SignatureAlgorithm.HS512, env.getProperty("token.secret"))
                .compact();

        response.addHeader("Authorization", "Bearer " + accessToken);
        response.addHeader("Access-Token-Expire", "" + accessExpiration);


        return ResponseDTO.ok("로그인 성공");
    }
}
