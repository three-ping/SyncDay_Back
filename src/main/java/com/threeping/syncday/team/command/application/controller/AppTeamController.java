package com.threeping.syncday.team.command.application.controller;

import com.threeping.syncday.common.ResponseDTO;
import com.threeping.syncday.team.command.aggregate.dto.TeamDTO;
import com.threeping.syncday.team.command.application.service.AppTeamServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/team")
public class AppTeamController {

    private final AppTeamServiceImpl appTeamService;

    @Autowired
    public AppTeamController(AppTeamServiceImpl appTeamService) {
        this.appTeamService = appTeamService;
    }

    @PostMapping("/create")
    public ResponseDTO<?> createTeam(@RequestBody TeamDTO teamDTO) {
        log.info("newTeamDTO: {}", teamDTO);
        return ResponseDTO.ok(appTeamService.addTeam(teamDTO));
    }

    @PutMapping("/update/{teamId}")
    public ResponseDTO<?> updateTeam(@PathVariable("teamId") Long teamId, @RequestBody TeamDTO teamDTO) {
        log.info("Updating team with ID {}: {}", teamId, teamDTO);
        return ResponseDTO.ok(appTeamService.modifyTeam(teamDTO));
    }


    @DeleteMapping("/{teamId}")
    public ResponseDTO<?> deleteTeam(@PathVariable("teamId") Long teamId) {
        return ResponseDTO.ok(appTeamService.deleteTeam(teamId));
    }
}
