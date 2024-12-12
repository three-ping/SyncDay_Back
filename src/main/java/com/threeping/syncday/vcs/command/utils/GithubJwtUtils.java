package com.threeping.syncday.vcs.command.utils;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.StringReader;
import java.nio.file.Files;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Security;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Component
public class GithubJwtUtils {
    private static final int TOKEN_EXPIRY_MINUTES = 10;

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    @Value("${github.app.private-key}")
    private String privateKeyString;

    @Value("${github.app.id}")
    private String githubAppId;

    private PrivateKey getPrivateKeyFromString() throws Exception {
        try {
            PEMParser pemParser = new PEMParser(new StringReader(privateKeyString));
            Object pemObject = pemParser.readObject();
            JcaPEMKeyConverter converter = new JcaPEMKeyConverter();

            if (pemObject instanceof PEMKeyPair) {
                return converter.getPrivateKey(((PEMKeyPair) pemObject).getPrivateKeyInfo());
            } else {
                return converter.getPrivateKey((PrivateKeyInfo) pemObject);
            }
        } catch (Exception e) {
            log.error("Failed to parse private key: {}", e.getMessage());
            throw new RuntimeException("Failed to generate private key", e);
        }
    }

    public String createJwt() {
        try {
            PrivateKey privateKey = getPrivateKeyFromString();

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.getForEntity("https://api.github.com/", String.class);
            HttpHeaders headers = response.getHeaders();
            long githubTime = headers.getDate();
            long timeDiff = System.currentTimeMillis() - githubTime;

            log.info("timeDiff: {}", timeDiff);

            long nowMillis = System.currentTimeMillis() - timeDiff;
            long expMillis = nowMillis + (TOKEN_EXPIRY_MINUTES * 60 * 1000);

            String token = Jwts.builder()
                    .setHeaderParam("alg", "RS256")
                    .claim("iat", new Date(nowMillis))
                    .claim("exp", new Date(expMillis))
                    .claim("iss", githubAppId)
                    .signWith(privateKey, SignatureAlgorithm.RS256)
                    .compact();

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