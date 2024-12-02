package com.threeping.syncday.vcs.command.domain.repository;

import com.threeping.syncday.vcs.command.aggregate.entity.VcsOrg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VcsOrgRepository extends JpaRepository<VcsOrg, Long> {

}
