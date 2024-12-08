package com.threeping.syncday.vcs.query.controller;

import com.threeping.syncday.common.ResponseDTO;
import com.threeping.syncday.vcs.query.service.GithubService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/github")
public class GithubController {

    private final GithubService githubService;

    @GetMapping("/install/users/{userId}")
    public ResponseDTO<?> findByUserId(@PathVariable("userId") Long userId) {
        return ResponseDTO.ok(githubService.getByUserId(userId));
    }
}
