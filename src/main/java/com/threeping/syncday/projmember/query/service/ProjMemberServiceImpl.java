package com.threeping.syncday.projmember.query.service;


import com.threeping.syncday.projmember.query.aggregate.ProjMember;
import com.threeping.syncday.projmember.query.aggregate.ProjMemberDTO;
import com.threeping.syncday.projmember.query.repository.ProjMemberMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjMemberServiceImpl implements ProjMemberService {

    private final ProjMemberMapper projMemberMapper;
    private final ModelMapper modelMapper;
    @Autowired
    public ProjMemberServiceImpl(
             ProjMemberMapper projMemberMapper
            ,ModelMapper modelMapper) {
        this.projMemberMapper = projMemberMapper;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProjMemberDTO> getAllProjMembers() {
        List<ProjMember> projMembers = projMemberMapper.selectAllProjMembers();
        List<ProjMemberDTO> projMemberDTOS = projMembers.stream().map(projMember -> modelMapper.map(projMember, ProjMemberDTO.class)).collect(Collectors.toList());
        return projMemberDTOS;
    }
}
