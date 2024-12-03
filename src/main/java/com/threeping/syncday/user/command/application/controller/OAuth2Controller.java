package com.threeping.syncday.user.command.application.controller;

import com.threeping.syncday.common.ResponseDTO;
import com.threeping.syncday.common.exception.CommonException;
import com.threeping.syncday.common.exception.ErrorCode;
import com.threeping.syncday.user.aggregate.oauth.vo.GithubAppInstallationRequestVO;
import com.threeping.syncday.user.aggregate.oauth.vo.GithubAppInstallationResponseVO;
import com.threeping.syncday.user.command.application.service.GithubAppInstallationService;
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
    private final GithubAppInstallationService githubAppInstallationService;
    private final OAuth2Service oAuth2Service;
    @Autowired
    public OAuth2Controller(GithubAppInstallationService githubAppInstallationService
    , OAuth2Service oAuth2Service) {
        this.githubAppInstallationService = githubAppInstallationService;
        this.oAuth2Service = oAuth2Service;
    }
    @PostMapping("/github/access_token")
    public ResponseDTO<?> getAccessToken(@RequestBody Map<String, String> request){
        String code = request.get("code");
        String state = request.get("state");
        log.info("code: {}", code);
        log.info("state: {}", state);
        if (code == null || state == null) {
            throw new CommonException(ErrorCode.GITHUB_AUTH_ERROR);
        }
        String accessToken = oAuth2Service.getGithubAccessToken(code);
        log.info("accessToken: {}", accessToken);
        return ResponseDTO.ok(accessToken);
    }

    @PostMapping("/github/installation")
    public ResponseDTO<?> handleInstallation(@RequestBody @Valid GithubAppInstallationRequestVO request) {
        try {
            Map<String, Object> installationInfo = githubAppInstallationService.validateInstallation(request.getInstallationId());
            Map<String, Object> response = new HashMap<>();

            response.put("installationInfo", installationInfo);

            return ResponseDTO.ok(response);
        } catch (Exception e) {
            log.error("Failed to handle GitHub installation", e);
            return ResponseDTO.fail(new CommonException(ErrorCode.INTERNAL_SERVER_ERROR));
        }
    }

}
