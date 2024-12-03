package com.threeping.syncday.vcs.command.domain.repository;

import com.threeping.syncday.vcs.command.aggreagate.entity.VcsOrg;
import com.threeping.syncday.vcs.command.aggreagate.entity.VcsType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VcsOrgRepository extends JpaRepository<VcsOrg, Long> {
    VcsOrg findByVcsOrgIdAndVcsType(Long vcsOrgId, VcsType vcsType);
}
