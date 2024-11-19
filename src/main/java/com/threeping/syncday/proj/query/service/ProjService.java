package com.threeping.syncday.proj.query.service;

import com.threeping.syncday.proj.query.aggregate.dto.ProjDTO;
import com.threeping.syncday.proj.query.aggregate.dto.ProjAndMemberDTO;

import java.util.List;

public interface ProjService {
    List<ProjDTO> getAllProjs();

    ProjDTO getProjById(Long projId);
}
