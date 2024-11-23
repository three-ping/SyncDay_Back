package com.threeping.syncday.workspace.command.application.controller;

import com.threeping.syncday.common.ResponseDTO;
import com.threeping.syncday.workspace.command.aggregate.vo.WorkspaceVO;
import com.threeping.syncday.workspace.command.application.service.AppWorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/workspaces")
public class AppWorkspaceController {

    private final AppWorkspaceService appWorkspaceService;

    @Autowired
    public AppWorkspaceController(AppWorkspaceService appWorkspaceService) {
        this.appWorkspaceService = appWorkspaceService;
    }

    @PostMapping()
    public ResponseDTO<?> createWorkspace(@RequestBody WorkspaceVO newWorkspace) {
        return ResponseDTO.ok(appWorkspaceService.addWorkspace(newWorkspace));
    }

    @PutMapping()
    public ResponseDTO<?> updateWorkspace(@RequestBody WorkspaceVO workspaceVO) {
        return ResponseDTO.ok(appWorkspaceService.modifyWorkspace(workspaceVO));
    }

    @DeleteMapping("/{workspaceId}")
    public ResponseDTO<?> deleteWorkspace(@PathVariable("workspaceId") Long workspaceId){
        return ResponseDTO.ok(appWorkspaceService.deleteWorkspace(workspaceId));
    }
}
