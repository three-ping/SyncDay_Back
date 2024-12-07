package com.threeping.syncday.projmember.command.application.controller;

import com.threeping.syncday.common.ResponseDTO;
import com.threeping.syncday.projmember.command.aggregate.vo.UpdateProjRequest;
import com.threeping.syncday.projmember.command.application.service.AppProjMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PutMapping("/bookmark")
    public ResponseDTO<?> updateProjBookmark(@RequestParam Long projMemberId) {

        return ResponseDTO.ok(appProjMemberService.updateProjBookmark(projMemberId));

    }


}
