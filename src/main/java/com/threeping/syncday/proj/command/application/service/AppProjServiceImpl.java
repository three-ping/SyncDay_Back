package com.threeping.syncday.proj.command.application.service;

import com.threeping.syncday.common.exception.CommonException;
import com.threeping.syncday.common.exception.ErrorCode;
import com.threeping.syncday.proj.command.aggregate.dto.NewProjDTO;
import com.threeping.syncday.proj.command.aggregate.dto.ProjDTO;
import com.threeping.syncday.proj.command.aggregate.entity.Proj;
import com.threeping.syncday.proj.command.domain.repository.ProjRepository;
import com.threeping.syncday.proj.command.infrastructure.service.InfraProjService;
import jakarta.transaction.Transactional;
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
    private final InfraProjService infraProjService;

    @Autowired
    public AppProjServiceImpl(ProjRepository projRepository
            , ModelMapper modelMapper
            , InfraProjService infraProjService) {
        this.projRepository = projRepository;
        this.modelMapper = modelMapper;
        this.infraProjService = infraProjService;
    }

    @Transactional
    @Override
    public ProjDTO addProj(NewProjDTO newProjDTO) {

        Proj newProj = modelMapper.map(newProjDTO, Proj.class);
        log.info("newProj: {}", newProj);

        Proj addedProj = projRepository.save(newProj);
        Boolean isUserAdded = infraProjService.requestAddProjOwner(addedProj.getProjId(), newProjDTO.getUserId());
        log.debug("isUserAdded: {}", isUserAdded);
        return modelMapper.map(addedProj, ProjDTO.class);
    }


    @Transactional
    @Override
    public ProjDTO deleteProj(Long projId) {
        Proj existingProj = projRepository.findByProjId(projId);
        if(existingProj == null) {
            throw new CommonException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
        projRepository.delete(existingProj);
        return modelMapper.map(existingProj, ProjDTO.class);
    }
}
