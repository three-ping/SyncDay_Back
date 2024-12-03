package com.threeping.syncday.vcs.command.application.service.service;

import com.threeping.syncday.vcs.command.aggreagate.vo.VcsInstallationCheckRequestVO;

public interface GithubInstallationService {
    Boolean checkGithubInstallation(VcsInstallationCheckRequestVO installationCheckVO);
}
