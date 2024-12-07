package com.threeping.syncday.projmember.command.infrastructure.service;

import com.threeping.syncday.proj.command.aggregate.dto.ProjDTO;
import com.threeping.syncday.proj.command.application.service.AppProjService;
import com.threeping.syncday.projmember.command.aggregate.vo.UpdateProjRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class InfraProjMemberServiceImpl implements InfraProjMemberService {
    private final AppProjService appProjService;


    @Override
    public ProjDTO requestAddProj(UpdateProjRequest req) {
        return appProjService.addProj(req);
    }
}
