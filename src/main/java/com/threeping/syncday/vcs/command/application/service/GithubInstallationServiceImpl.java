package com.threeping.syncday.vcs.command.application.service;

import com.threeping.syncday.vcs.command.aggreagate.vo.VcsInstallationRequestVO;
import lombok.extern.slf4j.Slf4j;
import org.kohsuke.github.GHAppInstallation;
import org.kohsuke.github.GHOrganization;
import org.kohsuke.github.GHUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class GithubInstallationServiceImpl implements GithubInstallationService {
    @Override
    public Boolean handleAppInstallation(VcsInstallationRequestVO installationRequestVO) {
        return null;
    }

    @Override
    public Boolean checkAppInstallation(String accountName) {
        return null;
    }

    @Override
    public void saveVcsOrg(Long userId, GHAppInstallation installation, GHUser user) {

    }

    @Override
    public void saveVcsOrg(Long userId, GHAppInstallation installation, GHOrganization org) {

    }
}
