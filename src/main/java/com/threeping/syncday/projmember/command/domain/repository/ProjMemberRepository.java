package com.threeping.syncday.projmember.command.domain.repository;

import com.threeping.syncday.projmember.command.aggregate.entity.ProjMember;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProjMemberRepository extends JpaRepository<ProjMember, Long> {
    ProjMember findByUserIdAndProjId(Long userId, Long projId);
}
