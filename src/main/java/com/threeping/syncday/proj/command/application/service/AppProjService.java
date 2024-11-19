package com.threeping.syncday.proj.command.application.service;


import com.threeping.syncday.proj.command.aggregate.dto.NewProjDTO;
import com.threeping.syncday.proj.command.aggregate.dto.ProjDTO;

public interface AppProjService {
    ProjDTO addProj(NewProjDTO newProj);

//    Modi modifyProj(ProjDTO projDTO);

    ProjDTO deleteProj(Long projId);
}
