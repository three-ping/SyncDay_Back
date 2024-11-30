package com.threeping.syncday.teampost.query.controller;

import com.github.pagehelper.PageInfo;
import com.threeping.syncday.common.ResponseDTO;
import com.threeping.syncday.teampost.query.aggregate.dto.TeamPostDTO;
import com.threeping.syncday.teampost.query.service.TeamPostQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teampost")
public class TeamPostQueryController {

    private final TeamPostQueryService teamPostQueryService;

    @Autowired
    public TeamPostQueryController(TeamPostQueryService teamPostQueryService) {
        this.teamPostQueryService = teamPostQueryService;
    }

    @GetMapping("/{teamBoardId}")
    public ResponseDTO<?> findTeamBoardPostPage(@PathVariable long teamBoardId,
                                                @RequestParam(defaultValue = "1") int page,
                                                @RequestParam(defaultValue = "10") int pageSize){
        PageInfo<TeamPostDTO> teamPostPageInfo = teamPostQueryService.findTeamBoardPostPage(teamBoardId,page,pageSize);
        return ResponseDTO.ok(teamPostPageInfo);
    }
}
