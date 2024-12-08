package com.threeping.syncday.github.query.controller;

import com.threeping.syncday.common.ResponseDTO;
import com.threeping.syncday.github.query.service.GithubRepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/github/repositories")
public class GithubRepositoryController {

    private final GithubRepositoryService githubRepositoryService;

    @GetMapping("/installations/{installationId}")
    public ResponseDTO<?> findByInstallationId(@PathVariable("installationId") Long installationId) {

        return ResponseDTO.ok(githubRepositoryService.getByInstallationId(installationId));
    }

}
