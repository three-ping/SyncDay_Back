package com.threeping.syncday.projmember.command.infrastructure.service;


import com.threeping.syncday.proj.command.aggregate.dto.ProjDTO;
import com.threeping.syncday.projmember.command.aggregate.vo.UpdateProjRequest;

public interface InfraProjMemberService {
    ProjDTO requestAddProj(UpdateProjRequest req);

    ProjDTO requestUpdateProj(UpdateProjRequest req);
}
