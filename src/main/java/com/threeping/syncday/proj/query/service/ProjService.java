package com.threeping.syncday.proj.query.service;

import com.threeping.syncday.proj.query.aggregate.ProjDTO;

import java.util.List;

public interface ProjService {
    List<ProjDTO> getAllProjs();

    List<ProjDTO> getProjsByUserId(Long userId);
}
