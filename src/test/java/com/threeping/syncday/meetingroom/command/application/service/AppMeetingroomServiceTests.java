package com.threeping.syncday.meetingroom.command.application.service;

import com.threeping.syncday.meetingroom.command.aggregate.dto.MeetingroomDTO;
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
class AppMeetingroomServiceTests {

    @Autowired
    AppMeetingroomService appMeetingroomService;

    @DisplayName("회의실 생성 테스트")
    @Test
    void testAddMeetingroom() {

        //given
        MeetingroomDTO meetingroomDTO = new MeetingroomDTO(null, "테스트 장소", "테스트 회의실", 10);

        //when
        MeetingroomDTO createdMeetingroom = appMeetingroomService.addMeetingroom(meetingroomDTO);

        //then
        assertNotNull(createdMeetingroom);
        assertNotNull(createdMeetingroom.getMeetingroomId());
        assertEquals("테스트 장소", createdMeetingroom.getMeetingroomPlace());
        assertEquals("테스트 회의실", createdMeetingroom.getMeetingroomName());
        assertEquals(10, createdMeetingroom.getMeetingroomCapacity());
    }

    @DisplayName("회의실 수정 테스트")
    @Test
    void testModifyMeetingroom() {

        // given - 회의실 생성
        MeetingroomDTO meetingroomDTO = new MeetingroomDTO(null, "초기 장소", "초기 회의실", 15);
        MeetingroomDTO createdMeetingroom = appMeetingroomService.addMeetingroom(meetingroomDTO);

        // When - 생성된 회의실을 수정
        createdMeetingroom.setMeetingroomPlace("수정된 장소");
        createdMeetingroom.setMeetingroomName("수정된 회의실");
        createdMeetingroom.setMeetingroomCapacity(20);
        MeetingroomDTO updatedMeetingroom = appMeetingroomService.modifyMeetingroom(createdMeetingroom);

        // Then
        assertNotNull(updatedMeetingroom);
        assertEquals(createdMeetingroom.getMeetingroomId(), updatedMeetingroom.getMeetingroomId());
        assertEquals("수정된 장소", updatedMeetingroom.getMeetingroomPlace());
        assertEquals("수정된 회의실", updatedMeetingroom.getMeetingroomName());
        assertEquals(20, updatedMeetingroom.getMeetingroomCapacity());
    }

    @DisplayName("회의실 삭제 테스트")
    @Test
    void testDeleteMeetingroom() {

        // given - 회의실 생성
        MeetingroomDTO meetingroomDTO = new MeetingroomDTO(null, "삭제 테스트 장소", "삭제 테스트 회의실", 25);
        MeetingroomDTO createdMeetingroom = appMeetingroomService.addMeetingroom(meetingroomDTO);

        // when - 생성된 회의실 삭제
        MeetingroomDTO deletedMeetingroom = appMeetingroomService.deleteMeetingroom(createdMeetingroom.getMeetingroomId());

        // then
        assertNotNull(deletedMeetingroom);
        assertEquals(createdMeetingroom.getMeetingroomId(), deletedMeetingroom.getMeetingroomId());
    }
}