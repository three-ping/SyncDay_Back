package com.threeping.syncday.vcs.command.application.controller;

import com.threeping.syncday.common.ResponseDTO;
import com.threeping.syncday.vcs.command.aggreagate.vo.VcsInstallationCheckRequestVO;
import com.threeping.syncday.vcs.command.aggreagate.vo.VcsInstallationRequestVO;
import com.threeping.syncday.vcs.command.application.service.service.VcsInstallationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/api/vcs")
public class AppVcsController {

    private final VcsInstallationService vcsInstallationService;

    @Autowired
    public AppVcsController(VcsInstallationService vcsInstallationService) {
        this.vcsInstallationService = vcsInstallationService;
    }

    @PostMapping("/install")
    public ResponseDTO<?> handleAppInstallation(@RequestBody VcsInstallationRequestVO requestVO){
        log.info("requestVO: {}", requestVO);
        this.vcsInstallationService.handleVcsInstallation(requestVO);
        log.info("requestVO: {}", requestVO);
        return ResponseDTO.ok(null);

    }

    @PostMapping("/install/check")
    public ResponseDTO<?> checkAppInstallation(@RequestBody VcsInstallationCheckRequestVO vcsInstallationCheckRequestVO){
        log.info("vcsInstallationCheckRequestVO: {}", vcsInstallationCheckRequestVO);
        return ResponseDTO.ok(vcsInstallationService.checkVcsInstallation(vcsInstallationCheckRequestVO));
    }
}
