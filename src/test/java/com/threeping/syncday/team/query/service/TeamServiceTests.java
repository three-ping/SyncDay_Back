package com.threeping.syncday.team.query.service;

import com.threeping.syncday.team.query.aggregate.TeamDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class TeamServiceTests {

    @Autowired
    private TeamService teamService;

    @DisplayName("팀 전체 조회")
    @Test
    void getAllTeams() {
        // given: 테스트 데이터를 미리 준비하는 경우

        // when: 서비스 메서드를 호출하여 결과를 조회
        List<TeamDTO> teamList = teamService.getAllTeams();

        // then: 결과 검증
        assertNotNull(teamList);
        assertFalse(teamList.isEmpty(), "팀 목록이 비어있지 않아야 합니다.");
        teamList.forEach(teamDTO -> {
            log.info("teamDTO: {}", teamDTO);
        });
    }
}