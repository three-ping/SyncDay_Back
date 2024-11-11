package com.threeping.syncday.projmember.command.application.service;

import com.threeping.syncday.projmember.command.aggregate.entity.ParticipationStatus;
import com.threeping.syncday.projmember.command.aggregate.entity.ProjMember;
import com.threeping.syncday.projmember.command.domain.repository.ProjMemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AppProjMemberServiceImpl implements AppProjMemberService {

    private final ProjMemberRepository projMemberRepository;

    @Autowired
    public AppProjMemberServiceImpl(ProjMemberRepository projMemberRepository) {
        this.projMemberRepository = projMemberRepository;
    }

    @Override
    public Boolean addProjOwner(Long projId, Long userId) {
        ProjMember newMember = new ProjMember();
        newMember.setProjId(projId);
        newMember.setUserId(userId);
        newMember.setParticipationStatus(ParticipationStatus.OWNER);
        log.debug("newMember: {}", newMember);
        ProjMember addedOwner =projMemberRepository.save(newMember);
        return addedOwner != null;
    }
}
