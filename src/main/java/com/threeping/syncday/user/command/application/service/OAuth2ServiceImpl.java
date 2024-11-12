package com.threeping.syncday.user.command.application.service;

import com.threeping.syncday.common.ResponseDTO;
import com.threeping.syncday.common.exception.CommonException;
import com.threeping.syncday.common.exception.ErrorCode;
import com.threeping.syncday.user.command.domain.vo.OAuth2LoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Slf4j
@Service
public class OAuth2ServiceImpl implements OAuth2Service {


    private RestTemplate restTemplate;
    private Environment env;
//    private final UserCommandService userCommandService;

    @Autowired
    public OAuth2ServiceImpl(RestTemplate restTemplate, Environment env) {
        this.restTemplate = restTemplate;
        this.env = env;
    }

    @Override
    public String getGithubAccessToken(String code) {
        String githubAuthUrl = "https://github.com/login/oauth/access_token";


        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("client_id", env.getProperty("spring.security.oauth2.client.registration.github.client-id"));
        params.add("client_secret", env.getProperty("spring.security.oauth2.client.registration.github.client-secret"));
        params.add("redirect_uri", env.getProperty("spring.security.oauth2.client.registration.github.redirect_uri"));
        params.add("code", code);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> githubTokenRequest = new HttpEntity<>(params, headers);
        try {
            ResponseEntity<Map> response = restTemplate.exchange(
                    githubAuthUrl,
                    HttpMethod.POST,
                    githubTokenRequest,
                    Map.class
            );

            Map<String, Object> responseBody = response.getBody();
            return (String) responseBody.get("access_token");

        } catch (HttpClientErrorException e) {
            log.error("Github API error: {}", e.getResponseBodyAsString());
            throw new CommonException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
        
    }
}
