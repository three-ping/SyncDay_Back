package com.threeping.syncday.vcs.command.application.controller;

import com.threeping.syncday.common.ResponseDTO;
import com.threeping.syncday.vcs.command.aggreagate.vo.VcsInstallationCheckRequestVO;
import com.threeping.syncday.vcs.command.aggreagate.vo.VcsInstallationRequestVO;
import com.threeping.syncday.vcs.command.application.service.service.GithubInstallationService;
import com.threeping.syncday.vcs.command.application.service.service.VcsInstallationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/api/vcs")
public class AppVcsController {

    private final VcsInstallationService vcsInstallationService;
    private final GithubInstallationService githubInstallationService;
    @Autowired
    public AppVcsController(VcsInstallationService vcsInstallationService
    , GithubInstallationService githubInstallationService) {
        this.vcsInstallationService = vcsInstallationService;
        this.githubInstallationService = githubInstallationService;
    }

    @PostMapping("/install")
    public ResponseDTO<?> handleAppInstallation(@RequestBody VcsInstallationRequestVO requestVO) {
        log.info("requestVO: {}", requestVO);
        return ResponseDTO.ok(this.vcsInstallationService.handleVcsInstallation(requestVO));

    }

    @PostMapping("/install/check")
    public ResponseDTO<?> checkAppInstallation(@RequestBody VcsInstallationCheckRequestVO vcsInstallationCheckRequestVO) {
        log.info("vcsInstallationCheckRequestVO: {}", vcsInstallationCheckRequestVO);
        return ResponseDTO.ok(vcsInstallationService.checkVcsInstallation(vcsInstallationCheckRequestVO));
    }

    @GetMapping("/installations/{installationId}")
    public ResponseDTO<?> findVcsInstallationById(@PathVariable("installationId") Long installationId) {
        return ResponseDTO.ok(vcsInstallationService.getVcsInstallation(installationId));
    }

    @GetMapping("/installations/{installationId}/projects")
    public ResponseDTO<?> findProjectsByVcsInstallationId(@PathVariable("installationId") Long installationId){
        return ResponseDTO.ok(vcsInstallationService.getOrganizationProjects(installationId));
    }

    @GetMapping("/installations/{installationId}/installation-token")
    public ResponseDTO<?> findInstallationToken(@PathVariable("installationId") Long installationId) throws IOException {
        return ResponseDTO.ok(githubInstallationService.getGithubAppInstallationToken(installationId));
    }
}
