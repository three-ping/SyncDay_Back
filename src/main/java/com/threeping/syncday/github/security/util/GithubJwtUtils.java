package com.threeping.syncday.github.security.util;

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

    @Value("${github.app.private-key-path}")
    private String privateKeyPath;

    private final ResourceLoader resourceLoader;

    public GithubJwtUtils(@Qualifier("webApplicationContext") ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    private PrivateKey getPrivateKey(String keyPath) throws Exception {
        Resource resource = resourceLoader.getResource(keyPath);
        byte[] keyBytes;
        try {
            keyBytes = Files.readAllBytes(resource.getFile().toPath());
        } catch (IOException e) {
            log.error("Failed to read private key file: {}", e.getMessage());
            throw new RuntimeException("Failed to read private key file", e);
        }
        log.info("keyBytes: {}", keyBytes);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        log.info("spec: {}", spec);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }


    private void validateToken(String token) {
        String[] parts = token.split("\\.");
        if (parts.length != 3) {
            throw new IllegalStateException("Invalid JWT token structure");
        }
        log.debug("JWT token parts: header={}, payload={}, signature={}",
                parts[0].length(), parts[1].length(), parts[2].length());
    }

    private void validatePrivateKey(Key key) {
        if (key == null) {
            throw new IllegalStateException("Private key is null");
        }
        if (!"RSA".equals(key.getAlgorithm())) {
            throw new IllegalStateException("Key algorithm must be RSA, found: " + key.getAlgorithm());
        }
        log.info("Private key validated - Algorithm: {}, Format: {}",
                key.getAlgorithm(), key.getFormat());
    }

    public void debugToken(String token) {
        try {
            String[] parts = token.split("\\.");
            String header = new String(java.util.Base64.getDecoder().decode(parts[0]));
            String payload = new String(java.util.Base64.getDecoder().decode(parts[1]));

            log.debug("Token header: {}", header);
            log.debug("Token payload: {}", payload);
        } catch (Exception e) {
            log.error("Failed to decode token for debugging", e);
        }
    }

    // Utility method to validate timestamps
    private boolean validateTimestamps(long iat, long exp) {
        long currentTime = System.currentTimeMillis() / 1000;

        // Validate that iat is not in the future
        if (iat > currentTime) {
            log.error("IAT timestamp is in the future: {}", iat);
            return false;
        }

        // Validate that exp is in the future but not too far
        if (exp <= currentTime || exp > (currentTime + 3600)) {
            log.error("Invalid expiration timestamp: {}", exp);
            return false;
        }

        return true;
    }
    public String createJwt() {
        try {
            // Use Instant for accurate timestamps
            Key privateKey = getPrivateKey(privateKeyPath);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.getForEntity("https://api.github.com/", String.class);
            HttpHeaders headers = response.getHeaders();
            long githubTime = headers.getDate();
            long timeDiff = System.currentTimeMillis() - githubTime;

            log.info("timeDiff: {}", timeDiff);
            System.out.println("Time difference with GitHub (ms): " + timeDiff);
            long nowMillis = System.currentTimeMillis() - timeDiff;
            long expMillis = nowMillis + 10 * 60 * 1000;
            // Build JWT with numeric timestamps for iat and exp
            String token = Jwts.builder()
                    .setHeaderParam("alg", "RS256")
                    .claim("iat", new Date(nowMillis))        // Unix timestamp in seconds
                    .claim("exp", new Date(expMillis)) // Unix timestamp in seconds
                    .claim("iss", githubAppId)                       // GitHub App ID
                    .signWith(privateKey, SignatureAlgorithm.RS256)
                    .compact();

            // Debug log the token contents
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

    // Utility method to validate the token format
    public boolean isValidTokenFormat(String token) {
        try {
            String[] parts = token.split("\\.");
            if (parts.length != 3) {
                log.error("Invalid token structure: expected 3 parts, got {}", parts.length);
                return false;
            }

            String payloadJson = new String(Base64.getUrlDecoder().decode(parts[1]));
            ObjectMapper mapper = new ObjectMapper();
            JsonNode payload = mapper.readTree(payloadJson);

            long now = Instant.now().getEpochSecond();
            long iat = payload.get("iat").asLong();
            long exp = payload.get("exp").asLong();

            if (iat > now) {
                log.error("Token iat is in the future: iat={}, now={}", iat, now);
                return false;
            }

            if (exp <= now) {
                log.error("Token is expired: exp={}, now={}", exp, now);
                return false;
            }

            return true;
        } catch (Exception e) {
            log.error("Token validation failed", e);
            return false;
        }
    }

}