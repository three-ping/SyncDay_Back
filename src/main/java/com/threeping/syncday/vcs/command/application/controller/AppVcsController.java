package com.threeping.syncday.vcs.command.application.controller;

import com.threeping.syncday.common.ResponseDTO;
import com.threeping.syncday.vcs.command.aggregate.vo.VcsInstallationVO;
import com.threeping.syncday.vcs.command.application.service.VcsInstallationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vcs/")
public class AppVcsController {

    private final VcsInstallationService vcsInstallationService;

    @Autowired
    public AppVcsController(VcsInstallationService vcsInstallationService) {
        this.vcsInstallationService = vcsInstallationService;
    }

    @PostMapping("/install")
    public ResponseDTO<?> installVcs(@RequestBody VcsInstallationVO vcsInstallationVO) {
        return ResponseDTO.ok(vcsInstallationService.handleVcsInstallation(vcsInstallationVO));
    }
}
