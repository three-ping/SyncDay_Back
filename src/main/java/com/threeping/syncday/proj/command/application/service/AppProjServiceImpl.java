package com.threeping.syncday.proj.command.application.service;

import com.threeping.syncday.proj.command.aggregate.entity.Proj;
import com.threeping.syncday.proj.command.domain.repository.ProjRepository;
import com.threeping.syncday.projmember.command.aggregate.vo.UpdateProjRequest;
import com.threeping.syncday.projmember.command.aggregate.vo.UpdateProjResponse;
import com.threeping.syncday.proj.command.aggregate.dto.ProjDTO;
import com.threeping.syncday.projmember.command.aggregate.vo.RequestUpdateVcsInfoVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppProjServiceImpl implements AppProjService {

    private final ProjRepository projRepository;
    private final ModelMapper modelMapper;

    @Override
    public ProjDTO addProj(UpdateProjRequest req) {
        Proj proj = new Proj();
        proj.setProjName(req.projName());
        proj.setStartTime(req.startTime());
        proj.setEndTime(req.endTime());
        proj.setVcsType(req.vcsType());
        proj.setVcsProjUrl(req.vcsProjUrl());

        Proj addedProj = projRepository.save(proj);
        return modelMapper.map(addedProj, ProjDTO.class);
    }
    @Override
    public ProjDTO updateProjVCS(RequestUpdateVcsInfoVO vcsInfo) {
        return null;
    }

    @Override
    public ProjDTO deleteProj(Long projId) {
        return null;
    }

    @Override
    public ProjDTO modifyProj(UpdateProjRequest req) {
        return null;
    }


}
