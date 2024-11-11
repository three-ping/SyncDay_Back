package com.threeping.syncday.proj.query.service;

import com.threeping.syncday.proj.query.aggregate.Proj;
import com.threeping.syncday.proj.query.aggregate.ProjDTO;
import com.threeping.syncday.proj.query.repository.ProjMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjServiceImpl implements ProjService {

    private final ProjMapper projMapper;
    private final ModelMapper modelMapper;

    @Autowired
    public ProjServiceImpl(ProjMapper projMapper, ModelMapper modelMapper) {
        this.projMapper = projMapper;
        this.modelMapper = modelMapper;
    }
    @Override
    public List<ProjDTO> getAllProjs() {
        List<Proj> projs = projMapper.selectAllProjs();
        List<ProjDTO> projDTOs =
                projs.stream().map(proj -> modelMapper.map(proj, ProjDTO.class)).collect(Collectors.toList());
        return projDTOs;
    }


    @Override
    public List<ProjDTO> getProjsByUserId(Long userId) {
        List<Proj> projs = projMapper.selectProjsByUserId(userId);
        List<ProjDTO> projDTOS
                 = projs.stream().map(proj -> modelMapper.map(proj, ProjDTO.class)).collect(Collectors.toList());
        return projDTOS;
    }
}
