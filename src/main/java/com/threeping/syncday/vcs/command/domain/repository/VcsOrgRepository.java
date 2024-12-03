package com.threeping.syncday.vcs.command.domain.repository;

import com.threeping.syncday.vcs.command.aggreagate.entity.VCSInstallation;
import com.threeping.syncday.vcs.command.aggreagate.entity.VcsType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VcsOrgRepository extends JpaRepository<VCSInstallation, Long> {
    VCSInstallation findByVcsOrgIdAndVcsType(Long vcsOrgId, VcsType vcsType);
}
