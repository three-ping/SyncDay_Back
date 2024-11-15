package com.threeping.syncday.team.command.application.service;

import com.threeping.syncday.team.command.aggregate.dto.TeamDTO;

public interface AppTeamService {
    TeamDTO addTeam(TeamDTO teamDTO);

    TeamDTO modifyTeam(TeamDTO teamDTO);

    TeamDTO deleteTeam(Long teamId);

    TeamDTO getTeamById(Long teamId);
}