package com.threeping.syncday.team.command.application.service;

import com.threeping.syncday.team.command.aggregate.dto.TeamDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
class AppTeamServiceTests {
    @Autowired
    AppTeamService appTeamService;

    @DisplayName("팀 생성 테스트")
    @Test
    void teatAddTeam() {

        //given
        String teamName = "뜨리핑";

        //when
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setTeamName(teamName);
        TeamDTO team = appTeamService.addTeam(teamDTO);

        //then
        assertNotNull(team.getTeamId());
        assertEquals(teamName, team.getTeamName());
    }

    @DisplayName("팀 수정 테스트")
    @Test
    void testModifyTeam() {

        //given
        String TeamName = "뜨리핑";
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setTeamName(TeamName);
        TeamDTO savedTeam = appTeamService.addTeam(teamDTO);

        // 준비된 팀의 ID와 새로운 이름을 설정
        Long teamId = savedTeam.getTeamId();
        String updatedTeamName = "시러핑";

        // 수정할 팀 데이터 준비
        TeamDTO updatedTeamDTO = new TeamDTO();
        updatedTeamDTO.setTeamId(teamId);
        updatedTeamDTO.setTeamName(updatedTeamName);

        // when: 팀 수정 메서드 호출
        TeamDTO modifiedTeam = appTeamService.modifyTeam(updatedTeamDTO);

        // then: 수정 결과 검증
        assertNotNull(modifiedTeam);
        assertEquals(teamId, modifiedTeam.getTeamId());
        assertEquals(updatedTeamName, modifiedTeam.getTeamName());
    }

    @DisplayName("팀 삭제 테스트")
    @Test
    void testDeleteTeam() {

        // given: 팀 생성
        String teamName = "뜨리핑";
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setTeamName(teamName);
        TeamDTO savedTeam = appTeamService.addTeam(teamDTO);

        // when: 팀 삭제 메서드 호출
        Long teamId = savedTeam.getTeamId();
        appTeamService.deleteTeam(teamId);

        // then: 팀이 삭제되었는지 확인
        TeamDTO deletedTeam = appTeamService.getTeamById(teamId);
        assertNull(deletedTeam);
    }
}