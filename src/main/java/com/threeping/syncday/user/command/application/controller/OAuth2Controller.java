package com.threeping.syncday.user.command.application.controller;

import com.threeping.syncday.common.ResponseDTO;
import com.threeping.syncday.common.exception.CommonException;
import com.threeping.syncday.common.exception.ErrorCode;
import com.threeping.syncday.user.aggregate.oauth.vo.GithubAppInstallationRequestVO;
import com.threeping.syncday.user.aggregate.oauth.vo.GithubAppInstallationResponseVO;
import com.threeping.syncday.user.command.application.service.GithubInstallationService;
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
    private final GithubInstallationService githubInstallationService;

    @Autowired
    public OAuth2Controller(GithubInstallationService githubInstallationService) {
        this.githubInstallationService = githubInstallationService;
    }

    @PostMapping("/github/installation")
    public ResponseDTO<?> handleInstallation(@RequestBody @Valid GithubAppInstallationRequestVO request) {
        try {
            GHAppInstallationToken token = githubInstallationService.getInstallationToken(request.getInstallationId());
            Map<String, Object> installationInfo = githubInstallationService.validateInstallation(request.getInstallationId());

            Map<String, Object> response = new HashMap<>();
            response.put("token", token.getToken());
            response.put("permissions", token.getPermissions());
            response.put("installationInfo", installationInfo);

            return ResponseDTO.ok(response);
        } catch (Exception e) {
            log.error("Failed to handle GitHub installation", e);
            return ResponseDTO.fail(new CommonException(ErrorCode.INTERNAL_SERVER_ERROR));
        }
    }

}
