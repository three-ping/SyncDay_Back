package com.threeping.syncday.user.security;

import com.threeping.syncday.common.exception.CommonException;
import com.threeping.syncday.common.exception.ErrorCode;
import com.threeping.syncday.user.query.service.UserQueryService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final UserQueryService userService;
    private final JwtUtil jwtUtil;
    private final RedisTemplate<String, String> redisTemplate;

    public JwtFilter(UserQueryService userService, JwtUtil jwtUtil, RedisTemplate<String, String> redisTemplate) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.redisTemplate = redisTemplate;
    }

    // authentication filter 전에 동작하는 필터(토큰 유효성 검사)
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String accessToken = request.getHeader("Authorization");

        // 1 ) AT Check
        if(accessToken == null || !accessToken.startsWith("Bearer ")) {
            log.info("access Token이 만료되거나 형식이 이상한 경우의 예외");
            throw new CommonException(ErrorCode.TOKEN_TYPE_ERROR);
        }

        try {
            // AT 유효성 검사
            if(jwtUtil.validateToken(accessToken)) {
                // 유효하다면 인증
                Authentication auth = getAuthentication(accessToken);
                SecurityContextHolder.getContext().setAuthentication(auth);
            } else {
                // 2 ) AT Expired
                handleExpiredAccessToken(request, response);
            }
        } catch (Exception e) {
            log.info("accessToken 유효성 검사 실패 시 예외");
            throw new CommonException(ErrorCode.TOKEN_MALFORMED_ERROR);
        }
        filterChain.doFilter(request, response);
    }

    public Authentication getAuthentication(String accessToken) {
        // 토큰에서 Claims 추출
        Claims claims = jwtUtil.parseClaims(accessToken);
        String email = claims.getSubject();

        List<String> roles = claims.get("auth", List.class);
        List<SimpleGrantedAuthority> authorities = roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        UserDetails userDetails = userService.loadUserByUsername(email);

        // Authentication 객체 생성 및 반환
        return new UsernamePasswordAuthenticationToken(userDetails,
                "",
                authorities);
    }

    private void handleExpiredAccessToken(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = request.getHeader("Refresh-Token");
        String userEmail = jwtUtil.getEmailFromToken(refreshToken);

        // 2-1 ) RT null 체크
        if(refreshToken == null) {
            // Redis에 TTL을 설정해놓았기 때문에 null == expired
            log.info("refresh token이 만료된 경우의 예외");
            throw new CommonException(ErrorCode.EXPIRED_TOKEN_ERROR);
        }

        String storesRefreshToken = redisTemplate.opsForValue().get("RT:" + userEmail);

        // 2-2) Redis에 저장된 Rt와 가져온 Rt 체크
        if(!storesRefreshToken.equals(refreshToken)) {
            redisTemplate.delete("RT:" + userEmail);
            log.info("저장된 rt와 가져온 rt가 일치하지 않을 경우의 예외(재로그인할 것)");
            throw new CommonException(ErrorCode.ACCESS_DENIED);
        }
        // 2-3) 이전 ip와 비교 후, RT 새로 발급할지 말지 결정?
        String newAccessToken = jwtUtil.generateAccessToken(userEmail);
        response.addHeader("Authorization", "Bearer " + newAccessToken);
    }
}
