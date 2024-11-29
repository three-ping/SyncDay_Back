package com.threeping.syncday.user.command.application.service;

import com.threeping.syncday.common.ResponseDTO;
import com.threeping.syncday.common.exception.CommonException;
import com.threeping.syncday.common.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
        params.add("client_id", env.getProperty("github.app.oauth2.client-id"));
        params.add("client_secret", env.getProperty("github.app.oauth2.client-secret"));
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
            log.info("responseBody: {}", responseBody);
            return (String) responseBody.get("access_token");

        } catch (HttpClientErrorException e) {
            log.error("Github API error: {}", e.getResponseBodyAsString());
            throw new CommonException(ErrorCode.INTERNAL_SERVER_ERROR);
        }

    }

    private Map<String, Object> getGithubUserInfo(String githubAccessToken) {
        String githubUserInfoUrl = "https://api.github.com/user";

        log.info("githubAccessToken: {}", githubAccessToken);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(githubAccessToken);

        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        log.info("headers: {}", headers);
        HttpEntity<MultiValueMap<String, String>> githubUserInfoRequest = new HttpEntity<>(headers);

        try {
            ResponseEntity<Map> response = restTemplate.exchange(
                    githubUserInfoUrl,
                    HttpMethod.GET,
                    githubUserInfoRequest,
                    Map.class
            );

            Map<String, Object> responseBody = response.getBody();
            Map<String, Object> githubAccount = (Map<String, Object>) responseBody.get("user");


            log.info("response: {}", response.getHeaders());
            log.info("responseBody: {}", responseBody);
            
            /* Todo: Response에 맞게 userInfo 수정 */
            Map<String, Object> userInfo = new HashMap<>();
            userInfo.put("id", githubAccount.get("id"));
            
            return userInfo;
            
        } catch (HttpClientErrorException e) {
            log.error("Github API error: {}", e.getResponseBodyAsString());
            throw e;
        }

    }

}
