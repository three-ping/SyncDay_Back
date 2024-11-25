package com.threeping.syncday.schedule.command.application.service;

import com.threeping.syncday.schedule.command.aggregate.dto.ScheduleDTO;
import com.threeping.syncday.schedule.command.aggregate.entity.MeetingStatus;
import com.threeping.syncday.schedule.command.aggregate.entity.PublicStatus;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
class AppScheduleServiceTests {

    @Autowired
    AppScheduleService appScheduleService;

    @DisplayName("일정을 생성한다.")
    @Test
    void testAddSchedule() {
        // given
        ScheduleDTO newScheduleDTO = new ScheduleDTO();
        newScheduleDTO.setUserId(1L);
        newScheduleDTO.setTitle("일정 생성 테스트");
        newScheduleDTO.setContent("생성 테스트 코드랍니다~");
        newScheduleDTO.setStartTime(Timestamp.from(Instant.now()));
        newScheduleDTO.setEndTime(Timestamp.from(Instant.now().plusSeconds(3600)));
        newScheduleDTO.setUpdateTime(Timestamp.from(Instant.now()));
        newScheduleDTO.setPublicStatus(PublicStatus.PRIVATE);
        newScheduleDTO.setScheduleRepeatId(null);
        newScheduleDTO.setRepeatOrder(null);
        newScheduleDTO.setMeetingStatus(MeetingStatus.ACTIVE);
        newScheduleDTO.setMeetingroomId(1L);
        newScheduleDTO.setAttendeeIds(List.of(2L, 3L));

        // when
        ScheduleDTO result = appScheduleService.addSchedule(newScheduleDTO);

        // then
        assertNotNull(result, "생성된 일정은 null이 아니어야 합니다.");
        assertEquals(newScheduleDTO.getTitle(), result.getTitle(), "일정 제목이 일치해야 합니다.");
        assertEquals(newScheduleDTO.getContent(), result.getContent(), "일정 내용이 일치해야 합니다.");
        assertNotNull(result.getScheduleId(), "일정 ID가 생성되어야 합니다.");
        log.info("일정 생성 테스트 성공: {}", result);
    }

    @DisplayName("일정을 수정한다.")
    @Test
    void testModifySchedule() {
        // given
        Long scheduleId = 7L;
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setUserId(6L);
        scheduleDTO.setTitle("일정 수정 테스트");
        scheduleDTO.setContent("수정 테스트 코드랍니다~~");
        scheduleDTO.setStartTime(Timestamp.from(Instant.now()));
        scheduleDTO.setEndTime(Timestamp.from(Instant.now().plusSeconds(3600)));
        scheduleDTO.setUpdateTime(Timestamp.from(Instant.now()));
        scheduleDTO.setPublicStatus(PublicStatus.PRIVATE);
        scheduleDTO.setScheduleRepeatId(null);
        scheduleDTO.setRepeatOrder(null);
        scheduleDTO.setMeetingStatus(MeetingStatus.ACTIVE);
        scheduleDTO.setMeetingroomId(1L);
        scheduleDTO.setAttendeeIds(List.of(4L, 5L));

        // when
        ScheduleDTO modifiedSchedule = appScheduleService.modifySchedule(scheduleDTO, scheduleId);

        // then
        assertNotNull(modifiedSchedule, "수정된 일정은 null이 아니어야 합니다.");
        assertEquals(scheduleDTO.getTitle(), modifiedSchedule.getTitle(), "수정된 일정 제목이 일치해야 합니다.");
        assertEquals(scheduleDTO.getContent(), modifiedSchedule.getContent(), "수정된 일정 내용이 일치해야 합니다.");
        assertEquals(scheduleId, modifiedSchedule.getScheduleId(), "수정된 일정 ID가 일치해야 합니다.");
        log.info("일정 수정 테스트 성공: {}", modifiedSchedule);
    }

    @DisplayName("일정을 삭제한다.")
    @Test
    void testDeleteSchedule() {
        // given
        Long scheduleId = 7L; // 삭제할 일정 ID

        // when
        ScheduleDTO deletedSchedule = appScheduleService.deleteSchedule(scheduleId);

        // then
        assertNotNull(deletedSchedule, "삭제된 일정은 null이 아니어야 합니다.");
        assertEquals(scheduleId, deletedSchedule.getScheduleId(), "삭제된 일정 ID가 일치해야 합니다.");
        log.info("일정 삭제 테스트 성공: {}", deletedSchedule);
    }
}