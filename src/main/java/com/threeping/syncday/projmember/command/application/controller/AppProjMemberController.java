package com.threeping.syncday.projmember.command.application.controller;

import com.threeping.syncday.common.ResponseDTO;
import com.threeping.syncday.projmember.command.aggregate.entity.vo.ProjBookmarkVO;
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

    @PutMapping("/bookmark/projMemberId")
    public ResponseDTO<?> toggleProjBookmark(@PathVariable("projMemberId") Long projMemberId){
        return ResponseDTO.ok(appProjMemberService.toggleProjBookmark(projMemberId));
    }
}
