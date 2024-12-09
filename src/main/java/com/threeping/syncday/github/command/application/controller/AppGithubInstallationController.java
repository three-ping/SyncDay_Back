package com.threeping.syncday.github.command.application.controller;

import com.threeping.syncday.common.ResponseDTO;
import com.threeping.syncday.github.command.aggregate.payload.GithubInstallationRequest;
import com.threeping.syncday.github.command.application.service.AppGithubInstallationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/github/install")
@Slf4j
public class AppGithubInstallationController {

    private final AppGithubInstallationService appGithubInstallationService;

    @Autowired
    public AppGithubInstallationController(AppGithubInstallationService appGithubInstallationService) {
        this.appGithubInstallationService = appGithubInstallationService;
    }

    @PostMapping("/")
    public ResponseDTO<?> handleGithubInstallationAuth(@RequestBody GithubInstallationRequest githubInstallationRequest){
        return ResponseDTO.ok(appGithubInstallationService.processInstallationAuth(githubInstallationRequest));
    }
}
