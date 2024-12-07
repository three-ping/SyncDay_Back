package com.threeping.syncday.github.query.controller;

import com.threeping.syncday.common.ResponseDTO;
import com.threeping.syncday.github.query.service.GithubInstallationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/github/install")
@Slf4j
@RequiredArgsConstructor
public class GithubInstallationController {

    private final GithubInstallationService githubInstallationService;

    @GetMapping("/{userId}")
    public ResponseDTO<?> findGithubInstallations(@PathVariable("userId") Long userId) {
        return ResponseDTO.ok(githubInstallationService.getInstallationsByUserId(userId));
    }

    @GetMapping("/{installationId}/projects")
    public ResponseDTO<?> findGithubProjects(@PathVariable("installationId") Long installationId) throws IOException {
        return ResponseDTO.ok(githubInstallationService.getProjectsByInstallationId(installationId));
    }

}
