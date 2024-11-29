package com.threeping.syncday.proj.query.service;

import com.threeping.syncday.proj.query.aggregate.dto.ProjAndWorkspaceDTO;
import com.threeping.syncday.proj.query.aggregate.dto.ProjDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class ProjServiceTests {

    @Autowired
    private ProjService projService;

    @DisplayName("프로젝트 전체 조회")
    @Test
    void testGetAllProjs() {

        //given

        // when
        List<ProjDTO> projList = projService.getAllProjs();

        // then
        assertNotNull(projList);
        projList.forEach(projDTO -> {
            log.info("projDTO: {}", projDTO);
        });
    }

    @DisplayName("프로젝트 ID를 통한 조회")
    @Test
    void testGetProjById() {

        // given
        Long projId = 1L;

        // when
        ProjDTO proj = projService.getProjById(projId);

        // then
        assertNotNull(proj);
        log.info("proj: {}", proj);
    }


    @DisplayName("유저 ID로 프로젝트와 워크스페이스 조회")
    @Test
    void testGetProjAndWorkspaceByUserId() {

        // given
        Long userId = 1L;

        // when
        List<ProjAndWorkspaceDTO> projs = projService.getProjInfosByUserId(userId);

        // then
        assertNotNull(projs);

        projs.forEach(projAndWorkspaceDTO -> {
            log.info("projAndWorkspaceDTO: {}", projAndWorkspaceDTO);
        });
    }


}