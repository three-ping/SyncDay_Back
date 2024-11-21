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

    @Override
    public List<ProjMemberDTO> getProjMembersByProjId(Long projId) {

        List<ProjMember> projMembers = projMemberMapper.selectProjMembersByProjId(projId);
        List<ProjMemberDTO> projMemberDTOS = projMembers.stream().map(projMember -> modelMapper.map(projMember, ProjMemberDTO.class)).collect(Collectors.toList());
        return projMemberDTOS;
    }


    /* 설명. 프로젝트 탭으로 이동시 유저의 아이디를 통해 프로젝트와 워크스페이스 정보 조회 */
    @Override
    public List<ProjMemberDTO> getProjsByUserId(Long userId) {
        List<ProjMember> projMembers = projMemberMapper.selectProjsByUserId(userId);
        List<ProjMemberDTO> projMemberDTOS = projMembers.stream().map(projMember -> modelMapper.map(projMember, ProjMemberDTO.class)).collect(Collectors.toList());
        return projMemberDTOS;
    }
}
