package com.threeping.syncday.user.command.application.controller;

import com.threeping.syncday.common.ResponseDTO;
import com.threeping.syncday.common.exception.CommonException;
import com.threeping.syncday.common.exception.ErrorCode;
import com.threeping.syncday.user.command.application.service.OAuth2Service;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.kohsuke.github.GHAppInstallationToken;
import org.kohsuke.github.GitHub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/user/oauth2")
public class OAuth2Controller {
    private final OAuth2Service oAuth2Service;
    @Autowired
    public OAuth2Controller(
    OAuth2Service oAuth2Service) {
        this.oAuth2Service = oAuth2Service;
    }
    @PostMapping("/github/access_token")
    public ResponseDTO<?> getAccessToken(@RequestBody Map<String, String> request){
        String code = request.get("code");
        String state = request.get("state");
        log.info("code: {}", code);
        log.info("state: {}", state);
        if (code == null || state == null) {
            throw new CommonException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
        String accessToken = oAuth2Service.getGithubAccessToken(code);
        log.info("accessToken: {}", accessToken);
        return ResponseDTO.ok(accessToken);
    }


}
