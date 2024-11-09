package com.threeping.syncday.proj.command.application.service;

import com.threeping.syncday.proj.command.aggregate.dto.ProjDTO;
import com.threeping.syncday.proj.command.aggregate.entity.Proj;
import com.threeping.syncday.proj.command.domain.repository.ProjRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Slf4j
@Service
public class AppProjServiceImpl implements AppProjService {

    private final ProjRepository projRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public AppProjServiceImpl(ProjRepository projRepository, ModelMapper modelMapper) {
        this.projRepository = projRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public ProjDTO addProj(ProjDTO newProjDTO) {

        Proj newProj = modelMapper.map(newProjDTO, Proj.class);
        log.info("newProj: {}", newProj);
        Proj addedProj = projRepository.save(newProj);
        return modelMapper.map(addedProj, ProjDTO.class);
    }
}
