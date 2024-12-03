package com.threeping.syncday.vcs.command.application.service.service;

import com.threeping.syncday.vcs.command.aggreagate.vo.VcsInstallationCheckRequestVO;
import com.threeping.syncday.vcs.command.aggreagate.vo.VcsInstallationRequestVO;

public interface GithubInstallationService {
    Boolean checkGithubInstallation(VcsInstallationCheckRequestVO installationCheckVO);

    void handleAppInstallation(VcsInstallationRequestVO requestVO);
}
