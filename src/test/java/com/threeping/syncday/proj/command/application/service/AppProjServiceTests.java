package com.threeping.syncday.proj.command.application.service;

import com.threeping.syncday.proj.command.aggregate.dto.ProjDTO;
import com.threeping.syncday.proj.command.aggregate.entity.Proj;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
class AppProjServiceTests {
    @Autowired
    AppProjService appProjService;

    @DisplayName("프로젝트 생성 테스트")
    @Test
    void testAddProj(){

        // given
        Long userId = 1L;
        String projName = "프로젝트 생성 및 프로젝트 오너 생성 테스트";

        // when
        ProjDTO projDTO = new ProjDTO();
        projDTO.setUserId(userId);
        projDTO.setProjName(projName);
        ProjDTO newProj = appProjService.addProj(projDTO);

        // then
        assertEquals(userId, newProj.getUserId());

        log.info("newProj: {}", newProj);
    }

    @DisplayName("프로젝트 수정 테스트")
    @Test
    void testModifyProj(){

        // given
        Long projId = 10L;
        String modifyProjName = "프로젝트 수정 테스트";

        // when
        ProjDTO projDTO = new ProjDTO();
        projDTO.setProjId(projId);
        projDTO.setProjName(modifyProjName);
        ProjDTO modifiedProj = appProjService.modifyProj(projDTO);

        // then
        assertEquals(modifyProjName, modifiedProj.getProjName());
        log.info("modifiedProj: {}", modifiedProj);
    }

    @DisplayName("프로젝트 삭제 테스트")
    @Test
    void testDeleteProj(){

        // given
        Long projId = 10L;

        // when
        ProjDTO projDTO = appProjService.deleteProj(projId);

        // then
        assertEquals(projId, projDTO.getProjId());

        log.info("deleteProj: {}", projDTO);
    }
}