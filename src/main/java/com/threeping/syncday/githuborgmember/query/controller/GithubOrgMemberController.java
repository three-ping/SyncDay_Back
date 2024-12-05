package com.threeping.syncday.githuborgmember.query.controller;

import com.threeping.syncday.githuborgmember.query.service.GithubOrgMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/github/org/member")
public class GithubOrgMemberController {

    private final GithubOrgMemberService githubOrgMemberService;

    @Autowired
    public GithubOrgMemberController(GithubOrgMemberService githubOrgMemberService) {
        this.githubOrgMemberService = githubOrgMemberService;
    }
}
