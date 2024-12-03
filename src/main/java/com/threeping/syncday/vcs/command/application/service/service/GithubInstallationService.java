package com.threeping.syncday.vcs.command.application.service.service;

import com.threeping.syncday.vcs.command.aggreagate.entity.VCSInstallation;
import com.threeping.syncday.vcs.command.aggreagate.vo.VcsInstallationCheckRequestVO;
import com.threeping.syncday.vcs.command.aggreagate.vo.VcsInstallationRequestVO;

public interface GithubInstallationService {
    Boolean checkGithubInstallation(VcsInstallationCheckRequestVO installationCheckVO);

    VCSInstallation handleGithubAppInstallation(VcsInstallationRequestVO requestVO);
}
