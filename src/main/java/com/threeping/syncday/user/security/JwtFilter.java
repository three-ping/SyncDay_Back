package com.threeping.syncday.user.security;

import com.threeping.syncday.common.exception.CommonException;
import com.threeping.syncday.common.exception.ErrorCode;
import com.threeping.syncday.user.query.service.UserQueryService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
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

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return path.startsWith("/api/user/login")
                || path.startsWith("/api/user/regist")
                || path.startsWith("/api/user/health")
                // Swagger UI 관련 모든 경로 허용
                || path.startsWith("/swagger-ui")
                || path.startsWith("/swagger-resources")
                || path.startsWith("/v3/api-docs")
                || path.startsWith("/api/docs/login")
                || path.startsWith("/swagger-custom-ui.html");
    }

    // authentication filter 전에 동작하는 필터(토큰 유효성 검사)
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        log.info("doFilterInternal method start");
        // 1 ) AT Check
        try {
            String accessToken = extractAccessToken(request);
            if(accessToken != null) {
                // AT 존재한다면 유효성 검사
                log.info("AT 유효성 검사 시작");
                processAccessToken(accessToken, request, response);
            }
        } catch (ExpiredJwtException e) {
            // 2 ) AT Expired
            log.info("AT 만료된 로그");
            handleExpiredAccessToken(request, response);
        } catch (JwtException e) {
            throw new CommonException(ErrorCode.TOKEN_UNKNOWN_ERROR);
        }
        filterChain.doFilter(request, response);
    }

    private void processAccessToken(String accessToken, HttpServletRequest request, HttpServletResponse response) {
        // redis에 BL(로그아웃한 회원의 accessToken인지 확인)
        log.info("accessToken 블랙리스트 체크 시작");
        String logout = redisTemplate.opsForValue().get("BL:"+ accessToken);
        // null이 아니다 == 해커가 탈취해서 재요청한 것(블랙리스트에 동륵되어 있는 거 가져옴)
        if(logout != null) {
            log.info("로그아웃된 accessToken을 가지고 온 경우 실행되는 예외");
            throw new CommonException(ErrorCode.LOGOUT_ACCESS_TOKEN);
        }
        log.info("processAccessToken method start");
        Claims claims = jwtUtil.parseClaims(accessToken);
        Authentication authentication = getAuthentication(claims);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public Authentication getAuthentication(Claims claims) {
        // 토큰에서 Claims 추출
        log.info("getAuthentication method 시작");
        String email = claims.getSubject();
        log.info("추출된 유저 email: {}", email);
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
            log.info("refresh token이 존재하지 않은 경우의 예외");
            throw new CommonException(ErrorCode.TOKEN_TYPE_ERROR);
        }

        String storesRefreshToken = redisTemplate.opsForValue().get("RT:" + userEmail);
        log.info("Redis에 저장된 storesRefreshToken: {}", storesRefreshToken);

        // 2-2 ) Redis에 저장된 토큰 null 체크
        if(storesRefreshToken == null) {
            // Redis에 TTL을 설정해놓았기 때문에 null == expired
            log.info("refresh token이 만료된 경우의 예외");
            throw new CommonException(ErrorCode.EXPIRED_TOKEN_ERROR);
        }

        // 2-3) Redis에 저장된 Rt와 가져온 Rt 체크
        if(!storesRefreshToken.equals(refreshToken)) {
            redisTemplate.delete("RT:" + userEmail);
            log.info("저장된 rt와 가져온 rt가 일치하지 않을 경우의 예외(재로그인할 것)");
            throw new CommonException(ErrorCode.ACCESS_DENIED);
        }
        // 2-4) 이전 ip와 비교 후, RT 새로 발급할지 말지 결정?
        String newAccessToken = jwtUtil.generateAccessToken(userEmail);
        response.addHeader("Authorization", "Bearer " + newAccessToken);
    }

    private String extractAccessToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if(bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
