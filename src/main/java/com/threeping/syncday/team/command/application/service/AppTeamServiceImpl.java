package com.threeping.syncday.team.command.application.service;

import com.threeping.syncday.common.exception.CommonException;
import com.threeping.syncday.common.exception.ErrorCode;
import com.threeping.syncday.team.command.aggregate.dto.TeamDTO;
import com.threeping.syncday.team.command.aggregate.entity.Team;
import com.threeping.syncday.team.command.domain.TeamRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class AppTeamServiceImpl implements AppTeamService {

    private final TeamRepository teamRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AppTeamServiceImpl(TeamRepository teamRepository
            , ModelMapper modelMapper) {
        this.teamRepository = teamRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public TeamDTO addTeam(TeamDTO teamDTO) {
        Team team = modelMapper.map(teamDTO, Team.class);
        log.info("team: {}", team);

        Team addedTeam = teamRepository.save(team);
        return modelMapper.map(addedTeam, TeamDTO.class);
    }

    @Transactional
    @Override
    public TeamDTO modifyTeam(TeamDTO teamDTO) {
        Team existingTeam = teamRepository.findByTeamId(teamDTO.getTeamId());
        if(existingTeam == null) {
            throw new CommonException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
        existingTeam.setTeamName(teamDTO.getTeamName());
        Team updatedTeam = teamRepository.save(existingTeam);

        return modelMapper.map(updatedTeam, TeamDTO.class);
    }

    @Transactional
    @Override
    public TeamDTO deleteTeam(Long teamId) {
        Team existingTeam = teamRepository.findByTeamId(teamId);
        if(existingTeam == null) {
            throw new CommonException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
        teamRepository.delete(existingTeam);
        return modelMapper.map(existingTeam, TeamDTO.class);
    }

        @Override
        public TeamDTO getTeamById(Long teamId) {
            Team team = teamRepository.findByTeamId(teamId);
            if (team == null) {
                return null; // 팀이 존재하지 않는 경우 null 반환 또는 예외 처리
            }
            return modelMapper.map(team, TeamDTO.class);
        }
    }

