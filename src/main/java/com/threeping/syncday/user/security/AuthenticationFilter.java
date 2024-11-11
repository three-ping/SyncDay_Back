package com.threeping.syncday.user.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.threeping.syncday.common.ResponseDTO;
import com.threeping.syncday.user.command.domain.vo.LoginRequestVO;
import com.threeping.syncday.user.command.domain.vo.ResponseNormalLoginVO;
import com.threeping.syncday.user.query.dto.UserDTO;
import com.threeping.syncday.user.query.service.UserQueryService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final UserQueryService userService;
    private final Environment env;
    private final RedisTemplate<String, String> redisTemplate;

    public AuthenticationFilter(AuthenticationManager authenticationManager,
                                UserQueryService userService,
                                Environment env,
                                RedisTemplate<String, String> redisTemplate) {
        super(authenticationManager);
        this.userService = userService;
        this.env = env;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        try {
            LoginRequestVO creds = new ObjectMapper().readValue(request.getInputStream(), LoginRequestVO.class);
            String email = creds.getEmil();

            // 인증 token 만들기
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(email, creds.getPassword(), new ArrayList<>())
            );
        } catch (IOException e) {
            throw new AuthenticationServiceException("유저 인증 실패", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        log.info("successfulAuthentication! Principal 객체 : {}", authResult);

        String email = ((User) authResult.getPrincipal()).getUsername();

        Claims claims = Jwts.claims().setSubject(email); // 회원 정보
        List<String> roles = authResult.getAuthorities().stream()
                .map(role -> role.getAuthority())
                .collect(Collectors.toList()); // 권한 정보
        claims.put("auth", roles);

        // 토큰 만료 시간 설정
        long accessExpiration =
                System.currentTimeMillis() + getExpirationTime(env.getProperty("token.access-expiration-time"));
        long refreshExpiration =
                System.currentTimeMillis() + getExpirationTime(env.getProperty("token.refresh-expiration-time"));

        // AT 생성
        String accessToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + accessExpiration))
                .signWith(SignatureAlgorithm.HS512, env.getProperty("token.secret"))
                .compact();

        // RT 생성
        String refreshToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshExpiration))
                .signWith(SignatureAlgorithm.HS512, env.getProperty("token.secret"))
                .compact();

        // Ex ) Key: RT:abc@gmail.com, Value: RT정보, TTL(Data 만료시간)
        redisTemplate.opsForValue().set(
                "RT:" + email,
                refreshToken,
                refreshExpiration,
                TimeUnit.MILLISECONDS);

        response.addHeader("Authorization", "Bearer " + accessToken);
        response.addHeader("Access-Token-Expire", "" + accessExpiration);
        response.addHeader("Refresh-Token", refreshToken);
        response.addHeader("Refresh-Token-Expire", "" + refreshExpiration);

        // user 상세 정보 조회
        UserDTO userDetails = userService.findByEmail(email);

        // 로그인 성공 후 바로 보여줄 응답객체, 아마 nav바에 들어갈 정보를 담을 듯?
        ResponseNormalLoginVO responseNormalLoginVO = new ResponseNormalLoginVO(
                userDetails.getUserId(),
                userDetails.getUserName(),
                userDetails.getEmail(),
                userDetails.getProfilePhoto(),
                userDetails.getJoinYear(),
                userDetails.getPosition(),
                userDetails.getTeamId()
        );

        ResponseDTO<ResponseNormalLoginVO> responseDTO = ResponseDTO.ok(responseNormalLoginVO);

        //JSON 문자열로 변환
        String JSON = new ObjectMapper().writeValueAsString(responseDTO);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(JSON);
    }

    private long getExpirationTime(String expirationTime) {
        if (expirationTime == null) {
            // 주어진 값이 없을 때 기본 시간 설정
            return 3600000;
        }
        return Long.parseLong(expirationTime);
    }
}
