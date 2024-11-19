package com.threeping.syncday.proj.command.application.service;

import com.threeping.syncday.proj.command.aggregate.dto.NewProjDTO;
import com.threeping.syncday.proj.command.aggregate.dto.ProjDTO;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
@Slf4j
class AppProjServiceTests {

    @Autowired
    private AppProjService appProjService;

    @DisplayName("프로젝트 생성 테스트")
    @Test
    void testCreateProject(){

        // given
        Long userId = 1L;
        String projName ="프로젝트 생성 테스트";

        // when
        NewProjDTO newProjDTO = new NewProjDTO();
        newProjDTO.setUserId(userId);
        newProjDTO.setProjName(projName);

        // then
        ProjDTO newProj = appProjService.addProj(newProjDTO);
        assertEquals(newProjDTO.getProjName(), newProj.getProjName());
        log.info("newProj: {}", newProj);
    }
}