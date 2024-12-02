package com.threeping.syncday.vcs.command.application.service;

import com.threeping.syncday.vcs.command.aggregate.entity.VcsOrg;
import com.threeping.syncday.vcs.command.aggregate.entity.VcsOrgStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class AppVcsServiceImpl implements AppVcsService {

    @Override
    public void updateStatus(Long installationId, VcsOrgStatus status) {

    }

    @Override
    public VcsOrg saveOrUpdate(VcsOrg vcsOrg) {
        return null;
    }
}
