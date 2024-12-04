package com.threeping.syncday.vcs.command.application.service.service;

import com.threeping.syncday.common.exception.CommonException;
import com.threeping.syncday.common.exception.ErrorCode;
import com.threeping.syncday.proj.command.application.service.AppProjService;
import com.threeping.syncday.vcs.command.aggreagate.entity.VCSInstallation;
import com.threeping.syncday.vcs.command.aggreagate.vo.VcsInstallationCheckRequestVO;
import com.threeping.syncday.vcs.command.aggreagate.vo.VcsInstallationRequestVO;
import com.threeping.syncday.vcs.command.aggreagate.vo.VcsInstallationResponse;
import com.threeping.syncday.vcs.command.domain.repository.VcsOrgRepository;
import lombok.extern.slf4j.Slf4j;
import org.kohsuke.github.GHProject;
import org.kohsuke.github.GitHub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class VcsInstallationServiceImpl implements VcsInstallationService {

    private final GithubInstallationService githubInstallationService;
    private final VcsOrgRepository vcsOrgRepository;

    @Autowired
    public VcsInstallationServiceImpl(GithubInstallationService githubInstallationService, AppProjService appProjService, VcsOrgRepository vcsOrgRepository) {
        this.githubInstallationService = githubInstallationService;
        this.vcsOrgRepository = vcsOrgRepository;
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

    @Override
    public VCSInstallation getVcsInstallation(Long installationId) {
        VCSInstallation vcsInstallation = vcsOrgRepository.findById(installationId).orElse(null);
        if (vcsInstallation == null) {
            throw new CommonException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
        return vcsInstallation;
    }

    @Override
    public List<GHProject> getOrganizationProjects(Long installationId) {
        VCSInstallation vcsInstallation = vcsOrgRepository.findById(installationId).orElse(null);
        if (vcsInstallation == null) {
            throw new CommonException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
        switch (vcsInstallation.getVcsType()) {
            case GITHUB:
                return githubInstallationService.getOrganizationProjects(vcsInstallation);

            case GITLAB:
                return null;
            default:
                break;
        }
        return null;
    }
}
