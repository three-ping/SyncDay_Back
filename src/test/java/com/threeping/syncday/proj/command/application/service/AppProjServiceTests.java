package com.threeping.syncday.proj.command.application.service;

import com.threeping.syncday.proj.command.aggregate.entity.VcsType;
import com.threeping.syncday.proj.command.aggregate.vo.ProjVO;
import com.threeping.syncday.proj.command.aggregate.dto.ProjDTO;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;

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
        ProjVO projVO = new ProjVO();
        projVO.setUserId(userId);
        projVO.setProjName(projName);

        // then
        ProjDTO newProj = appProjService.addProj(projVO);
        assertEquals(projVO.getProjName(), newProj.getProjName());
        log.info("newProj: {}", newProj);
    }


    @DisplayName("프로젝트 수정 테스트")
    @Test
    void testUpdateProject(){
        // given
        Long projId = 1L;
        String modifyProjName = "프로젝트 수정 테스트";
        Long userId = 1L;
        Timestamp startTime = new Timestamp(System.currentTimeMillis());
        VcsType vcsType = VcsType.GITHUB;
        String url = "github.com";

        ProjVO projVO = new ProjVO();
        projVO.setProjId(projId);
        projVO.setUserId(userId);
        projVO.setProjName(modifyProjName);
        projVO.setStartTime(startTime);
        projVO.setVcsType(vcsType);
        projVO.setVcsProjUrl(url);

        // when
        ProjDTO newProj = appProjService.modifyProj(projVO);

        assertEquals(projVO.getProjName(), newProj.getProjName());
        log.info("newProj: {}", newProj);


    }

    @DisplayName("프로젝트 삭제 테스트")
    @Test
    void testDeleteProject(){

        // given
        Long projId = 1L;

        // when
        ProjDTO deleteProjDTO = appProjService.deleteProj(projId);

        assertEquals(projId, deleteProjDTO.getProjId());
        log.info("deleteProjDTO: {}", deleteProjDTO);

    }
}