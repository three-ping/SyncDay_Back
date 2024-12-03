package com.threeping.syncday.user.command.domain.repository;

import com.threeping.syncday.user.aggregate.oauth.entity.VcsOrg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VcsOrgRepository extends JpaRepository<VcsOrg, Long> {
    VcsOrg findByInstallationId(Long installationId);
}
