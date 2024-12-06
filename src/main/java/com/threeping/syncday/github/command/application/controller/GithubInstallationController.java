package com.threeping.syncday.github.command.application.controller;

import com.threeping.syncday.common.ResponseDTO;
import com.threeping.syncday.github.command.aggregate.vo.InstallationAuthRequest;
import com.threeping.syncday.github.command.application.service.GithubInstallationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/github/installation")
@Slf4j
public class GithubInstallationController {

    private final GithubInstallationService githubInstallationService;

    @Autowired
    public GithubInstallationController(GithubInstallationService githubInstallationService) {
        this.githubInstallationService = githubInstallationService;
    }

    @PostMapping("/auth")
    public ResponseDTO<?> handleGithubInstallationAuth(@RequestBody InstallationAuthRequest githubInstallationRequest) {
        return ResponseDTO.ok(githubInstallationService.processInstallationAuth(githubInstallationRequest));
    }
}
