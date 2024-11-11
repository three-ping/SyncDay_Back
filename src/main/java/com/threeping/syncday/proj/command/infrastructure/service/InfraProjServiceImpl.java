package com.threeping.syncday.proj.command.infrastructure.service;

import com.threeping.syncday.projmember.command.application.service.AppProjMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfraProjServiceImpl implements InfraProjService {

    private final AppProjMemberService appProjMemberService;

    @Autowired
    public InfraProjServiceImpl(AppProjMemberService appProjMemberService) {
        this.appProjMemberService = appProjMemberService;
    }

    @Override
    public Boolean requestAddProjOwner(Long projId, Long userId) {
        return appProjMemberService.addProjOwner(projId, userId);
    }
}
