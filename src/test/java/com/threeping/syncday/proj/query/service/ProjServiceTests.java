package com.threeping.syncday.proj.query.service;

import com.threeping.syncday.proj.query.aggregate.ProjDTO;
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
    void testGetAllProjs(){

        //given

        // when
        List<ProjDTO> projList = projService.getAllProjs();

        // then
        assertNotNull(projList);
        projList.forEach(projDTO -> {
            log.info("projDTO: {}", projDTO);
        });
    }


    @DisplayName("유저 아이디로 프로젝트 조회")
    @Test
    void testGetProjsByUserId(){

        // given
        Long userId = 1L;

        // when
        List<ProjDTO> projList = projService.getProjsByUserId(userId);

        // then
        assertNotNull(projList);
        projList.forEach(projDTO -> {
            log.info("projDTO: {}", projDTO);
        });
    }
}