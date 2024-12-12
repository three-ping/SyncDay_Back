package com.threeping.syncday.vcs.command.utils;

import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.StringReader;
import java.security.PrivateKey;
import java.security.Security;

@Slf4j
@Component
public class GithubJwtUtils {
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    @Value("${github.app.private-key}")
    private String privateKeyString;

    private PrivateKey getPrivateKeyFromString() throws Exception {
        try {
            // PEM 형식의 문자열을 읽기 위한 PEMParser 생성
            PEMParser pemParser = new PEMParser(new StringReader(privateKeyString));

            // PEM 객체를 읽음
            Object pemObject = pemParser.readObject();

            // PEM을 Java 키로 변환하기 위한 컨버터
            JcaPEMKeyConverter converter = new JcaPEMKeyConverter();

            if (pemObject instanceof PEMKeyPair) {
                // Key pair인 경우
                return converter.getPrivateKey(((PEMKeyPair) pemObject).getPrivateKeyInfo());
            } else {
                // PrivateKeyInfo인 경우
                return converter.getPrivateKey((PrivateKeyInfo) pemObject);
            }
        } catch (Exception e) {
            log.error("Failed to parse private key: {}", e.getMessage());
            throw new RuntimeException("Failed to generate private key", e);
        }
    }
}