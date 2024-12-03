package com.threeping.syncday.vcs.command.application.service.service;

import com.threeping.syncday.vcs.command.aggreagate.vo.VcsInstallationCheckRequestVO;
import com.threeping.syncday.vcs.command.aggreagate.vo.VcsInstallationRequestVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VcsInstallationServiceImpl implements VcsInstallationService {

    private final GithubInstallationService githubInstallationService;

    public VcsInstallationServiceImpl(GithubInstallationService githubInstallationService) {
        this.githubInstallationService = githubInstallationService;
    }

    @Override
    public Boolean checkVcsInstallation(VcsInstallationCheckRequestVO installationCheckVO) {
        switch (installationCheckVO.getVcsType()){
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
    public void handleVcsInstallation(VcsInstallationRequestVO requestVO) {
        switch(requestVO.getVcsType()){
            case GITHUB:  githubInstallationService.handleAppInstallation(requestVO);
            break;
            case GITLAB: break;
            default:
                break;

        }
    }
}
