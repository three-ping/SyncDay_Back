package com.threeping.syncday.proj.command.application.service;


import com.threeping.syncday.proj.command.aggregate.dto.ProjVO;
import com.threeping.syncday.proj.command.aggregate.dto.ProjDTO;

public interface AppProjService {
    ProjDTO addProj(ProjVO projVO);

    ProjDTO modifyProj(ProjVO projVO);

    ProjDTO deleteProj(Long projId);
}
