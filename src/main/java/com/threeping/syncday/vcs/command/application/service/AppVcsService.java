package com.threeping.syncday.vcs.command.application.service;

import com.threeping.syncday.vcs.command.aggregate.entity.VcsOrg;
import com.threeping.syncday.vcs.command.aggregate.entity.VcsOrgStatus;

public interface AppVcsService {
    VcsOrg saveOrUpdate(VcsOrg vcsOrg);

    void updateStatus(Long installationId, VcsOrgStatus status);

}
