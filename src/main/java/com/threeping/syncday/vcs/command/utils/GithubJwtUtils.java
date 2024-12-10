package com.threeping.syncday.vcs.command.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;


@Slf4j
@Component
public class GithubJwtUtils {
    private static final int TOKEN_EXPIRY_MINUTES = 10;

    @Value("${github.app.id}")
    private String githubAppId;

    // private-key-path 대신 private-key를 직접 주입받습니다
    @Value("${github.app.private-key}")
    private String privateKeyBase64;

    // 메서드 이름을 더 명확하게 변경하고, Base64로 인코딩된 문자열에서 PrivateKey를 생성합니다
    private PrivateKey getPrivateKeyFromBase64() throws Exception {
        try {
            // Base64로 인코딩된 문자열을 디코딩하여 바이트 배열로 변환합니다
            byte[] keyBytes = Base64.getDecoder().decode(privateKeyBase64);
            log.info("Successfully decoded private key from Base64");

            // PKCS8 형식으로 private key를 생성합니다
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            return kf.generatePrivate(spec);
        } catch (IllegalArgumentException e) {
            log.error("Invalid Base64 encoding in private key: {}", e.getMessage());
            throw new RuntimeException("Invalid private key format", e);
        } catch (Exception e) {
            log.error("Failed to generate private key: {}", e.getMessage());
            throw new RuntimeException("Failed to generate private key", e);
        }
    }



    public String createJwt() {
        try {
            // 파일 대신 Base64 문자열에서 private key를 생성합니다
            Key privateKey = getPrivateKeyFromBase64();

            // GitHub 서버 시간과의 동기화를 위한 시간 차이를 계산합니다
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.getForEntity("https://api.github.com/", String.class);
            HttpHeaders headers = response.getHeaders();
            long githubTime = headers.getDate();
            long timeDiff = System.currentTimeMillis() - githubTime;

            log.info("Time difference with GitHub (ms): {}", timeDiff);

            // JWT 토큰 생성 시간을 설정합니다
            long nowMillis = System.currentTimeMillis() - timeDiff;
            long expMillis = nowMillis + (TOKEN_EXPIRY_MINUTES * 60 * 1000);

            String token = Jwts.builder()
                    .setHeaderParam("alg", "RS256")
                    .claim("iat", new Date(nowMillis))
                    .claim("exp", new Date(expMillis))
                    .claim("iss", githubAppId)
                    .signWith(privateKey, SignatureAlgorithm.RS256)
                    .compact();

            // 디버깅을 위해 토큰 내용을 로깅합니다
            logDecodedToken(token);

            return token;
        } catch (Exception e) {
            log.error("JWT Token generation failed", e);
            throw new RuntimeException("Failed to generate JWT token", e);
        }
    }

    private void logDecodedToken(String token) {
        try {
            String[] parts = token.split("\\.");

            String headerJson = new String(Base64.getUrlDecoder().decode(parts[0]));
            String payloadJson = new String(Base64.getUrlDecoder().decode(parts[1]));

            log.info("JWT Token Structure:");
            log.info("Header: {}", headerJson);
            log.info("Payload: {}", payloadJson);
            log.info("Full Token: {}", token);

            // Parse the payload to verify timestamps
            ObjectMapper mapper = new ObjectMapper();
            JsonNode payload = mapper.readTree(payloadJson);

            log.info("Current time (epoch): {}", Instant.now().getEpochSecond());
            log.info("Token iat: {}", payload.get("iat").asLong());
            log.info("Token exp: {}", payload.get("exp").asLong());
            log.info("Token iss: {}", payload.get("iss").asText());

        } catch (Exception e) {
            log.error("Failed to decode JWT token for debugging", e);
        }
    }
}