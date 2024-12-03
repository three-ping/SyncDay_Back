package com.threeping.syncday.vcs.command.application.service;


import com.threeping.syncday.vcs.command.aggreagate.vo.VcsInstallationRequestVO;
import org.kohsuke.github.GHAppInstallation;
import org.kohsuke.github.GHOrganization;
import org.kohsuke.github.GHUser;

public interface GithubInstallationService {
    Boolean handleAppInstallation(VcsInstallationRequestVO installationRequestVO);
    void saveVcsOrg(Long userId, GHAppInstallation installation, GHOrganization org);
    void saveVcsOrg(Long userId, GHAppInstallation installation, GHUser user);
    Boolean checkAppInstallation(String accountName);

}

