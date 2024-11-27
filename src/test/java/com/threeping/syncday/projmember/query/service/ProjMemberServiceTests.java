package com.threeping.syncday.projmember.query.service;

import com.threeping.syncday.projmember.query.aggregate.ProjMember;
import com.threeping.syncday.projmember.query.aggregate.ProjMemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class ProjMemberServiceTests {

    @Autowired
    private ProjMemberService projMemberService;

    @DisplayName("프로젝트 멤버 전체 조회")
    @Test
    void getAllProjMembers(){

        // given

        // when
        List<ProjMemberDTO> projMembers = projMemberService.getAllProjMembers();

        // then
        assertNotNull(projMembers);

        projMembers.forEach(x-> log.info("x: {}", x));
    }

    @DisplayName("프로젝트 ID로 멤버 리스트 조회")
    @Test
    void getProjMemberByProjId(){

        // given
        Long projId = 1L;

        // when
        List<ProjMemberDTO> projMemberList = projMemberService.getProjMembersByProjId(projId);

        // then
        assertNotNull(projMemberList);
        projMemberList.forEach(x-> log.info("x: {}", x));
    }



}