package com.threeping.syncday.proj.command.application.service;


import com.threeping.syncday.projmember.command.aggregate.vo.UpdateProjRequest;
import com.threeping.syncday.proj.command.aggregate.dto.ProjDTO;
import com.threeping.syncday.projmember.command.aggregate.vo.RequestUpdateVcsInfoVO;

public interface AppProjService {
    ProjDTO addProj(UpdateProjRequest req);

    ProjDTO modifyProj(UpdateProjRequest req);

    ProjDTO deleteProj(java.lang.Long projId);

    ProjDTO updateProjVCS(RequestUpdateVcsInfoVO vcsInfo);
}
