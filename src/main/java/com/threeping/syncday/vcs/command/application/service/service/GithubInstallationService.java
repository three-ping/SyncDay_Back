package com.threeping.syncday.vcs.command.application.service.service;

import com.threeping.syncday.vcs.command.aggreagate.entity.VCSInstallation;
import com.threeping.syncday.vcs.command.aggreagate.vo.VcsInstallationCheckRequestVO;
import com.threeping.syncday.vcs.command.aggreagate.vo.VcsInstallationRequestVO;
import com.threeping.syncday.vcs.command.aggreagate.vo.VcsInstallationResponse;
import org.kohsuke.github.GHAppInstallationToken;
import org.kohsuke.github.GHProject;

import java.io.IOException;
import java.util.List;

public interface GithubInstallationService {
    Boolean checkGithubInstallation(VcsInstallationCheckRequestVO installationCheckVO);

    VcsInstallationResponse handleGithubAppInstallation(VcsInstallationRequestVO requestVO);

    List<GHProject> getOrganizationProjects(VCSInstallation vcsInstallation);

    String getGithubAppInstallationToken(Long vcsInstallationId) throws IOException;

}
