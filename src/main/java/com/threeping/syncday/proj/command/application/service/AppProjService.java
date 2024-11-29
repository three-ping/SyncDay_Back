package com.threeping.syncday.proj.command.application.service;


import com.threeping.syncday.proj.command.aggregate.vo.ProjVO;
import com.threeping.syncday.proj.command.aggregate.dto.ProjDTO;
import com.threeping.syncday.proj.command.aggregate.vo.RequestUpdateVcsInfoVO;

public interface AppProjService {
    ProjDTO addProj(ProjVO projVO);

    ProjDTO modifyProj(ProjVO projVO);

    ProjDTO deleteProj(Long projId);

    ProjDTO updateVcsInfo(RequestUpdateVcsInfoVO requestUpdateVcsInfoVO);
}
