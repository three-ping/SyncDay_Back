package com.threeping.syncday.vcs.command.application.service.service;

import com.threeping.syncday.vcs.command.aggreagate.vo.VcsInstallationCheckRequestVO;
import com.threeping.syncday.vcs.command.domain.repository.VcsOrgRepository;
import org.springframework.stereotype.Service;

@Service
public interface VcsInstallationService {
    Boolean checkVcsInstallation(VcsInstallationCheckRequestVO installationCheckVO);


}
