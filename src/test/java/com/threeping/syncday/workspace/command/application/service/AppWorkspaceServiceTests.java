package com.threeping.syncday.workspace.command.application.service;

import com.threeping.syncday.workspace.command.aggregate.vo.WorkspaceVO;
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
class AppWorkspaceServiceTests {

    @Autowired
    private AppWorkspaceService appWorkspaceService;

    @DisplayName("워크스페이스 생성 테스트")
    @Test
    void testAddWorkspace(){

        // given
        Long projId = 1L;
        String workspaceName = "test";

        // when

        WorkspaceVO workspaceVO = new WorkspaceVO();
        workspaceVO.setWorkspaceName(workspaceName);
        workspaceVO.setProjId(projId);
        WorkspaceVO addedWorkspace = appWorkspaceService.addWorkspace(workspaceVO);

        // then
        assertEquals(workspaceVO.getWorkspaceName(), addedWorkspace.getWorkspaceName());

        log.info("addedWorkspace: {}", addedWorkspace);

        }
}