package com.threeping.syncday.proj.command.application.service;

import com.threeping.syncday.common.exception.CommonException;
import com.threeping.syncday.common.exception.ErrorCode;
import com.threeping.syncday.proj.command.aggregate.vo.ProjVO;
import com.threeping.syncday.proj.command.aggregate.dto.ProjDTO;
import com.threeping.syncday.proj.command.aggregate.entity.Proj;
import com.threeping.syncday.proj.command.aggregate.vo.RequestUpdateVcsInfoVO;
import com.threeping.syncday.proj.command.domain.repository.ProjRepository;
import com.threeping.syncday.proj.command.infrastructure.service.InfraProjService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public ProjDTO addProj(ProjVO projVO) {

        Proj newProj = modelMapper.map(projVO, Proj.class);
        log.info("newProj: {}", newProj);

        Proj addedProj = projRepository.save(newProj);
        Boolean isOwnerAdded = infraProjService.requestAddProjOwner(addedProj.getProjId(), projVO.getUserId());
        log.info("isOwnerAdded: {}", isOwnerAdded);
        return modelMapper.map(addedProj, ProjDTO.class);
    }

    @Transactional
    @Override
    public ProjDTO modifyProj(ProjVO projVO) {



        /* todo: 유효성 검증  */
        Proj foundProj = projRepository.findById(projVO.getProjId()).orElse(null);
        log.info("foundProj: {}", foundProj);
        if (foundProj == null) {
            throw new CommonException(ErrorCode.PROJ_NOT_FOUND);
        }
        foundProj.setProjName(projVO.getProjName());
        foundProj.setStartTime(projVO.getStartTime());
        foundProj.setEndTime(projVO.getEndTime());
        foundProj.setVcsProjUrl(projVO.getVcsProjUrl());

        Proj modifiedProj = projRepository.save(foundProj);
        return modelMapper.map(modifiedProj, ProjDTO.class);
    }

    @Transactional
    @Override
    public ProjDTO deleteProj(Long projId) {
        Proj existingProj = projRepository.findByProjId(projId);
        if (existingProj == null) {
            throw new CommonException(ErrorCode.PROJ_NOT_FOUND);
        }
        projRepository.delete(existingProj);
        return modelMapper.map(existingProj, ProjDTO.class);
    }

    @Override
    public ProjDTO updateProjVCS(RequestUpdateVcsInfoVO vcsInfo) {

        Proj existingProj = projRepository.findByProjId(vcsInfo.getProjId());
        if(existingProj == null){
            throw new CommonException(ErrorCode.PROJ_NOT_FOUND);
        }
        existingProj.setVcsType(vcsInfo.getVcsType());
        existingProj.setVcsProjUrl(vcsInfo.getVcsProjUrl());
        return modelMapper.map(projRepository.save(existingProj), ProjDTO.class);
    }


}
