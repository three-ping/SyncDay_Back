package com.threeping.syncday.projmember.command.application.controller;

import com.threeping.syncday.common.ResponseDTO;
import com.threeping.syncday.projmember.command.aggregate.vo.UpdateProjRequest;
import com.threeping.syncday.projmember.command.aggregate.vo.UpdateWorkspaceRequest;
import com.threeping.syncday.projmember.command.application.service.AppProjMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/api/proj-members")
public class AppProjMemberController {

    private final AppProjMemberService appProjMemberService;

    @Autowired
    public AppProjMemberController(AppProjMemberService appProjMemberService) {
        this.appProjMemberService = appProjMemberService;
    }

    @PostMapping("/")
    public ResponseDTO<?> createProj(@RequestBody UpdateProjRequest req){
        return ResponseDTO.ok(appProjMemberService.addProj(req));
    }

    @PutMapping("/")
    public ResponseDTO<?> updateProj(@RequestBody UpdateProjRequest req){
        log.info("req: {}", req);
        return ResponseDTO.ok(appProjMemberService.updateProj(req));
    }
    @PutMapping("/bookmark")
    public ResponseDTO<?> updateProjBookmark(@RequestParam Long projMemberId) {

        return ResponseDTO.ok(appProjMemberService.updateProjBookmark(projMemberId));

    }

    @PutMapping("/workspaces")
    public ResponseDTO<?> updateWorkspace(@RequestBody UpdateWorkspaceRequest updateWorkspaceRequest){
        log.info("updateWorkspaceRequest: {}", updateWorkspaceRequest);
        return ResponseDTO.ok(appProjMemberService.updateWorkspace(updateWorkspaceRequest));
    }


}
