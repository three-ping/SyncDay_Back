package com.threeping.syncday.projmember.command.application.controller;

import com.threeping.syncday.common.ResponseDTO;
import com.threeping.syncday.projmember.command.aggregate.entity.vo.ProjBookmarkVO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/proj-members")
public class AppProjMemberController {
    @PutMapping("/")
    public ResponseDTO<?> bookmarkProj(@RequestBody ProjBookmarkVO bookmarkVO){
        return null;
    }
}
