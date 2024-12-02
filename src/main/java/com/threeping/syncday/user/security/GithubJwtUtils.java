// GithubJwtUtils.java
package com.threeping.syncday.user.security;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Date;

@Slf4j
@Component
public class GithubJwtUtils {

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

        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }

    public String createJWT() throws Exception {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.RS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        // GitHub API requires exp to be no more than 10 minutes in the future
        Date exp = new Date(nowMillis + (10 * 60 * 1000));

        // Use classpath or file system path as appropriate
        Key signingKey = getPrivateKey(privateKeyPath);
        validatePrivateKey(signingKey);
        JwtBuilder builder = Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(exp)
                .setIssuer(githubAppId)
                .setHeaderParam("alg","RS256")
                .setHeaderParam("typ", "JWT")
                .signWith(signingKey, signatureAlgorithm);

        String token = builder.compact();
        log.debug("Generated JWT token for GitHub App {}", githubAppId);
        log.info("JWT Token structure: {}", token.split("\\.").length); // Should be 3
        validateToken(token);
        log.info("token: {}", token);
        return token;
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
}