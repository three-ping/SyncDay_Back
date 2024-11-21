package com.threeping.syncday.workspace.query.controller;

import com.threeping.syncday.common.ResponseDTO;
import com.threeping.syncday.workspace.query.service.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("api/workspaces")
public class WorkspaceController {

    private final WorkspaceService workSpaceService;

    @Autowired
    public WorkspaceController(WorkspaceService workSpaceService) {
        this.workSpaceService = workSpaceService;
    }

    @GetMapping("/")
    public ResponseDTO<?> findAllWorkspaces(){
        return ResponseDTO.ok(workSpaceService.getAllWorkspaces());
    }

    @GetMapping("/{projId}")
    public ResponseDTO<?> findWorkspacesByProjId(@PathVariable("projId") Long projId){
        return ResponseDTO.ok(workSpaceService.getWorkspacesByProjId(projId));
    }
}
