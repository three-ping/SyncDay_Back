package com.threeping.syncday.github.query.controller;

import com.threeping.syncday.common.ResponseDTO;
import com.threeping.syncday.github.query.service.GithubMilestoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/github/milestones")
public class GithubMilestoneController {
    private final GithubMilestoneService githubMilestoneService;;

    @GetMapping("/{repoId}")
    public ResponseDTO<?> getMilestones(@PathVariable Long repoId) {
        return ResponseDTO.ok(githubMilestoneService.getMilestones(repoId));

    }
}
