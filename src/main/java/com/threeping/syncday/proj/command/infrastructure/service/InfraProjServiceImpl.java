package com.threeping.syncday.proj.command.infrastructure.service;

import com.threeping.syncday.projmember.command.application.service.AppProjMemberService;
import com.threeping.syncday.projmember.query.service.ProjMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfraProjServiceImpl implements InfraProjService {

    private final AppProjMemberService appProjMemberService;
    private final ProjMemberService projMemberService;

    @Autowired
    public InfraProjServiceImpl(AppProjMemberService appProjMemberService
            , ProjMemberService projMemberService) {
        this.appProjMemberService = appProjMemberService;
        this.projMemberService = projMemberService;
    }

    @Override
    public Boolean requestAddProjOwner(Long projId, Long userId) {
        return appProjMemberService.addProjOwner(projId, userId);
    }


}
