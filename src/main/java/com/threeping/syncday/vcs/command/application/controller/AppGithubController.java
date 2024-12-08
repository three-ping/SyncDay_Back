package com.threeping.syncday.vcs.command.application.controller;

import com.threeping.syncday.common.ResponseDTO;
import com.threeping.syncday.vcs.command.aggregate.request.GithubInstallationRequest;
import com.threeping.syncday.vcs.command.application.service.AppGithubService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/github")
public class AppGithubController
{
    private final AppGithubService githubService;
    @PostMapping("/install")
    public ResponseDTO<?> installGithubApp(@RequestBody GithubInstallationRequest req){
        return ResponseDTO.ok(githubService.handleInstallation(req));
    }


}
