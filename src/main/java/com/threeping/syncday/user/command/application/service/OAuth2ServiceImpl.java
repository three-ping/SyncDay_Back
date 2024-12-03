package com.threeping.syncday.user.command.application.service;

import com.threeping.syncday.common.ResponseDTO;
import com.threeping.syncday.common.exception.CommonException;
import com.threeping.syncday.common.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class OAuth2ServiceImpl implements OAuth2Service {

    @Value("${github.oauth2.client-id}")
    private String clientId;

    @Value("${github.oauth2.client-secret}")
    private String clientSecret;

    private RestTemplate restTemplate;
    private Environment env;

    @Autowired
    public OAuth2ServiceImpl(RestTemplate restTemplate, Environment env) {
        this.restTemplate = restTemplate;
        this.env = env;
    }


    public String getGithubAccessToken(String code) {
        String githubAuthUrl = "https://github.com/login/oauth/access_token";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("code", code);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Accept", "application/json"); // Add this to get JSON response

        HttpEntity<MultiValueMap<String, String>> githubTokenRequest = new HttpEntity<>(params, headers);

        try {
            ResponseEntity<Map> response = restTemplate.exchange(
                    githubAuthUrl,
                    HttpMethod.POST,
                    githubTokenRequest,
                    Map.class
            );

            Map<String, Object> responseBody = response.getBody();

            // Check for error in response
            if (responseBody.containsKey("error")) {
                log.error("Github OAuth error: {} - {}",
                        responseBody.get("error"),
                        responseBody.get("error_description"));
                throw new CommonException(ErrorCode.GITHUB_AUTH_ERROR);
            }

            String accessToken = (String) responseBody.get("access_token");
            if (accessToken == null || accessToken.isEmpty()) {
                throw new CommonException(ErrorCode.GITHUB_AUTH_ERROR);
            }

            return accessToken;

        } catch (HttpClientErrorException e) {
            log.error("Github API error: {}", e.getResponseBodyAsString());
            throw new CommonException(ErrorCode.GITHUB_AUTH_ERROR);
        }
    }

}