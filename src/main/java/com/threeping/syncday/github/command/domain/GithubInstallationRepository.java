package com.threeping.syncday.github.command.domain;

import com.threeping.syncday.github.command.aggregate.entity.GithubInstallationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GithubInstallationRepository extends JpaRepository<GithubInstallationEntity, Long> {
}
