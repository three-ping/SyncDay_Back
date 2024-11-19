package com.threeping.syncday.meetingroom.query.service;

import com.threeping.syncday.meetingroom.query.aggregate.MeetingroomDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class MeetingroomServiceTests {

    @Autowired
    private MeetingroomService meetingroomService;

    @DisplayName("회의실 전체 조회")
    @Test
    void testGetAllMeetingrooms() {
        //given

        //when: 서비스 매소드를 호출하여 결과를 조회
        List<MeetingroomDTO> meetingroomList = meetingroomService.getAllMeetingrooms();

        //then: 결과 검증
        assertNotNull(meetingroomList);
        assertFalse(meetingroomList.isEmpty(), "회의실 목록이 비어있지 않아야 합니다.");
        meetingroomList.forEach(meetingroomDTO -> {
            log.info("meetingroomDTO: {}", meetingroomDTO);
        });

    }

//    @DisplayName("팀 아이디로 회의실 조회")
//    @Test
//    void testGetMeetingroomsByTeamId() {
//
//
//    }
}