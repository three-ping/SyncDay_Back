package com.threeping.syncday.projmember.query.controller;

import com.threeping.syncday.common.ResponseDTO;
import com.threeping.syncday.projmember.query.service.ProjMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/proj-members")
public class ProjMemberController {

    private final ProjMemberService projMemberService;

    @Autowired
    public ProjMemberController(ProjMemberService projMemberService) {
        this.projMemberService = projMemberService;
    }


    @GetMapping("/")
    public ResponseDTO<?> findAllProjMember() {
        return ResponseDTO.ok(projMemberService.getAllProjMembers());
    }

    @GetMapping("/users/{userId}")
    public ResponseDTO<?> findAllProjsByUserId(@PathVariable("userId") Long userId) {
        return ResponseDTO.ok(projMemberService.getProjsByUserId(userId));
    }
    @GetMapping("/projs/{projId}")
    public ResponseDTO<?> findProjMemberById(@PathVariable("projId") Long projId) {

        return ResponseDTO.ok(projMemberService.getProjMembersByProjId(projId));
    }
}
