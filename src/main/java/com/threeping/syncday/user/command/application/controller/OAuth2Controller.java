package com.threeping.syncday.user.command.application.controller;

import com.threeping.syncday.common.ResponseDTO;
import com.threeping.syncday.common.exception.CommonException;
import com.threeping.syncday.common.exception.ErrorCode;
import com.threeping.syncday.user.aggregate.oauth.vo.GithubAppInstallationVO;
import com.threeping.syncday.user.command.application.service.GithubInstallationService;
import lombok.extern.slf4j.Slf4j;
import org.kohsuke.github.GHAppInstallationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ResponseDTO<?> handleInstallation(@RequestBody
            GithubAppInstallationVO githubAppInstallationVO) {
        log.info("githubAppInstallationVO: {}", githubAppInstallationVO);
        try {
            GHAppInstallationToken installationToken = githubInstallationService.getInstallationToken(githubAppInstallationVO.getInstallationId());
            return ResponseDTO.ok(installationToken);
        } catch (Exception e) {
            throw new CommonException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }
}
