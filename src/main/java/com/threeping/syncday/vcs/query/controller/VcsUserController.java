package com.threeping.syncday.vcs.query.controller;

import com.threeping.syncday.common.ResponseDTO;
import com.threeping.syncday.vcs.common.enums.VcsType;
import com.threeping.syncday.vcs.query.aggregate.vo.ReqUserInstallationCheck;
import com.threeping.syncday.vcs.query.service.VcsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vcs/user")
public class VcsUserController {

    private final VcsUserService vcsUserService;

    @Autowired
    public VcsUserController(VcsUserService vcsUserService) {
        this.vcsUserService = vcsUserService;
    }

    @GetMapping("/check")
    public ResponseDTO<?> checkUserGithubInstallation(@RequestParam("userId") Long userId, @RequestParam("vcsType") VcsType vcsType) {
        return ResponseDTO.ok(vcsUserService.checkUserInstallation(new ReqUserInstallationCheck(userId, vcsType)));
    }
}
