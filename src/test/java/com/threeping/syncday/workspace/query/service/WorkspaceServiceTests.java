package com.threeping.syncday.workspace.query.service;

import com.threeping.syncday.workspace.query.aggregate.WorkspaceDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class WorkspaceServiceTests {

    @Autowired
    private WorkspaceService workspaceService;

    @DisplayName("워크스페이스 전체 조회 테스트")
    @Test
    void testGetAllWorkspaces(){

        // given

        // when
        List<WorkspaceDTO> workspaceDTOS = workspaceService.getAllWorkspaces();

        // then
        assertNotNull(workspaceDTOS);

        workspaceDTOS.forEach(x-> log.info("x: {}", x));
    }

    @DisplayName("프로젝트 ID로 워크스페이스 조회")
    @Test
    void testGetWorkspaceByProjId(){

        // given
        Long projId = 1L;

        // when
        List<WorkspaceDTO> workspaceDTOS = workspaceService.getWorkspacesByProjId(projId);
        assertNotNull(workspaceDTOS);

        workspaceDTOS.forEach(x-> log.info("x: {}", x));
    }
}