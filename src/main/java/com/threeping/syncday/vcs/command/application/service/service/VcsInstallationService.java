package com.threeping.syncday.vcs.command.application.service.service;

import com.threeping.syncday.vcs.command.aggreagate.entity.VCSInstallation;
import com.threeping.syncday.vcs.command.aggreagate.vo.VcsInstallationCheckRequestVO;
import com.threeping.syncday.vcs.command.aggreagate.vo.VcsInstallationRequestVO;
import com.threeping.syncday.vcs.command.aggreagate.vo.VcsInstallationResponse;
import com.threeping.syncday.vcs.command.domain.repository.VcsOrgRepository;
import org.kohsuke.github.GHProject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VcsInstallationService {
    Boolean checkVcsInstallation(VcsInstallationCheckRequestVO installationCheckVO);


    VcsInstallationResponse handleVcsInstallation(VcsInstallationRequestVO requestVO);

    VCSInstallation getVcsInstallation(Long installationId);

    List<GHProject> getOrganizationProjects(Long installationId);
}
