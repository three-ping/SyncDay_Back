package com.threeping.syncday.vcs.command.application.service.service;

import com.threeping.syncday.proj.command.application.service.AppProjService;
import com.threeping.syncday.vcs.command.aggreagate.vo.VcsInstallationCheckRequestVO;
import com.threeping.syncday.vcs.command.aggreagate.vo.VcsInstallationRequestVO;
import com.threeping.syncday.vcs.command.aggreagate.vo.VcsInstallationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VcsInstallationServiceImpl implements VcsInstallationService {

    private final GithubInstallationService githubInstallationService;

    @Autowired
    public VcsInstallationServiceImpl(GithubInstallationService githubInstallationService, AppProjService appProjService) {
        this.githubInstallationService = githubInstallationService;
     ;
    }

    @Override
    public Boolean checkVcsInstallation(VcsInstallationCheckRequestVO installationCheckVO) {
        switch (installationCheckVO.getVcsType()) {
            case GITHUB:
                return githubInstallationService.checkGithubInstallation(installationCheckVO);
            case GITLAB:
                break;
            default:
                break;
        }
        return null;
    }

    @Override
    public VcsInstallationResponse handleVcsInstallation(VcsInstallationRequestVO requestVO) {
        switch (requestVO.getVcsType()) {
            case GITHUB:
                return githubInstallationService.handleGithubAppInstallation(requestVO);
            case GITLAB:
                break;
            default:
                break;
        }
        return null;
    }
}
