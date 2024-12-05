package com.threeping.syncday.vcs.command.application.service;

import com.threeping.syncday.vcs.command.aggregate.vo.VcsInstallationResponse;
import com.threeping.syncday.vcs.command.aggregate.vo.VcsInstallationVO;

public interface VcsInstallationService {
    VcsInstallationResponse handleVcsInstallation(VcsInstallationVO vcsInstallationVO);
}
