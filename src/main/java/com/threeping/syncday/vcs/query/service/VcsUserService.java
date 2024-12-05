package com.threeping.syncday.vcs.query.service;

import com.threeping.syncday.vcs.common.enums.VcsType;
import com.threeping.syncday.vcs.query.aggregate.vo.ReqUserInstallationCheck;

public interface VcsUserService {
    Boolean checkUserInstallation(ReqUserInstallationCheck reqUserInstallationCheck);
}
