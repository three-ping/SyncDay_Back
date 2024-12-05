package com.threeping.syncday.vcs.query.controller;

import com.threeping.syncday.vcs.query.service.VcsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vcs/user")
public class VcsUserController {

    private final VcsUserService vcsUserService;

    @Autowired
    public VcsUserController(VcsUserService vcsUserService) {
        this.vcsUserService = vcsUserService;
    }
}
