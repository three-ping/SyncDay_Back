package com.threeping.syncday.proj.command.application.service;


import com.threeping.syncday.proj.command.aggregate.dto.ProjDTO;

public interface AppProjService {
    ProjDTO addProj(ProjDTO newProj);

    ProjDTO modifyProj(ProjDTO projDTO);

    ProjDTO deleteProj(Long projId);
}
