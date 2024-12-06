package com.threeping.syncday.user.command.application.controller;

import com.threeping.syncday.common.ResponseDTO;
import com.threeping.syncday.common.exception.CommonException;
import com.threeping.syncday.common.exception.ErrorCode;
import com.threeping.syncday.user.command.aggregate.vo.GithubAuthRequestVO;
import com.threeping.syncday.user.command.application.service.OAuth2Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/oauth2")
@Slf4j
public class OAuth2Controller {

    private final OAuth2Service oAuth2Service;

    @Autowired
    public OAuth2Controller(OAuth2Service oAuth2Service) {
        this.oAuth2Service = oAuth2Service;
    }

    @PostMapping("/github/access_token")

    public ResponseDTO<?> getAccessToken(@RequestBody GithubAuthRequestVO request){
        String code = request.getCode();
        String state = request.getState();
        log.info("code: {}", code);
        log.info("state: {}", state);
        if (code == null || state == null) {
            throw new CommonException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
        return ResponseDTO.ok(oAuth2Service.getGithubAccessToken(code));
    }
}
