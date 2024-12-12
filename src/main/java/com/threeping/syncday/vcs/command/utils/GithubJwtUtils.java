package com.threeping.syncday.vcs.command.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.StringReader;
import java.security.PrivateKey;
import java.security.Security;
import java.util.Date;

@Slf4j
@Component
public class GithubJwtUtils {
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    @Value("${github.app.id}")
    private String githubAppId;

    @Value("${github.app.private-key}")
    private String privateKeyString;

    private PrivateKey getPrivateKeyFromString() throws Exception {
        try {
            // Base64로 인코딩된 키를 디코딩
            String normalizedKey = privateKeyString
                    .replace("\\n", "\n")  // 이스케이프된 개행문자 처리
                    .replace(" ", "")      // 공백 제거
                    .trim();               // 앞뒤 공백 제거

            // PEM 형식이 아닌 경우 PEM 형식으로 변환
            if (!normalizedKey.startsWith("-----BEGIN")) {
                normalizedKey = "-----BEGIN PRIVATE KEY-----\n" +
                        normalizedKey + "\n" +
                        "-----END PRIVATE KEY-----";
            }

            // PEM 파싱
            PEMParser pemParser = new PEMParser(new StringReader(normalizedKey));
            Object pemObject = pemParser.readObject();

            // PEM을 Java 키로 변환
            JcaPEMKeyConverter converter = new JcaPEMKeyConverter()
                    .setProvider(BouncyCastleProvider.PROVIDER_NAME);

            if (pemObject instanceof PEMKeyPair) {
                return converter.getPrivateKey(((PEMKeyPair) pemObject).getPrivateKeyInfo());
            } else if (pemObject instanceof PrivateKeyInfo) {
                return converter.getPrivateKey((PrivateKeyInfo) pemObject);
            } else {
                throw new IllegalArgumentException("Unsupported key format");
            }
        } catch (Exception e) {
            log.error("Failed to parse private key: ", e);
            throw new RuntimeException("Failed to generate private key", e);
        }
    }

    public String createJwt() {
        try {
            // GitHub API 시간 동기화
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.getForEntity("https://api.github.com/", String.class);
            HttpHeaders headers = response.getHeaders();
            long githubTime = headers.getDate();
            long timeDiff = System.currentTimeMillis() - githubTime;

            log.info("timeDiff: {}", timeDiff);

            // JWT 생성에 사용할 시간 계산
            long nowMillis = System.currentTimeMillis() - timeDiff;
            long expMillis = nowMillis + 10 * 60 * 1000; // 10분 후 만료

            // private key 가져오기
            PrivateKey privateKey = getPrivateKeyFromString();

            // JWT 토큰 생성
            String token = Jwts.builder()
                    .setHeaderParam("alg", "RS256")
                    .claim("iat", new Date(nowMillis))
                    .claim("exp", new Date(expMillis))
                    .claim("iss", githubAppId)
                    .signWith(privateKey, SignatureAlgorithm.RS256)
                    .compact();

            return token;
        } catch (Exception e) {
            log.error("JWT Token generation failed", e);
            throw new RuntimeException("Failed to generate JWT token", e);
        }
    }
}