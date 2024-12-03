package com.threeping.syncday.vcs.command.application.service.service;

import com.threeping.syncday.vcs.command.aggreagate.entity.VcsOrg;
import com.threeping.syncday.vcs.command.aggreagate.vo.VcsInstallationCheckRequestVO;
import com.threeping.syncday.vcs.command.domain.repository.VcsOrgRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class GithubInstallationServiceImpl implements GithubInstallationService {

    private final VcsOrgRepository vcsOrgRepository;

    @Autowired
    public GithubInstallationServiceImpl(VcsOrgRepository vcsOrgRepository) {
        this.vcsOrgRepository = vcsOrgRepository;
    }

    @Override
    public Boolean checkGithubInstallation(VcsInstallationCheckRequestVO installationCheckVO) {
        VcsOrg vcsOrg = vcsOrgRepository.findByVcsOrgIdAndVcsType(installationCheckVO.getVcsOrgId(), installationCheckVO.getVcsType());
        if (vcsOrg!=null){
            log.info("vcsOrg: {}", vcsOrg);
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }
}
