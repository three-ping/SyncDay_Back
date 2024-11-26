package com.threeping.syncday.scheduleparticipant.command.application.service;

import com.threeping.syncday.scheduleparticipant.command.aggregate.entity.ScheduleParticipant;
import com.threeping.syncday.scheduleparticipant.command.domain.repository.ScheduleParticipantRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
class AppScheduleParticipantServiceTests {

    @Autowired
    AppScheduleParticipantService appScheduleParticipantService;

    @Autowired
    ScheduleParticipantRepository scheduleParticipantRepository;

    @DisplayName("일정 참석자를 추가한다.")
    @Test
    void testAddScheduleParticipant() {
        // given
        Long userId = 12L; // 요청 사용자 ID
        Long scheduleId = 11L; // 일정 ID
        List<Long> attendeeIds = List.of(4L, 5L); // 참석자 ID 목록
        Timestamp notificationTime = Timestamp.valueOf("2024-11-25T10:00:00.000+09:00");

        // when
        appScheduleParticipantService.addScheduleParticipant(userId, scheduleId, attendeeIds, notificationTime);

        // then
        List<ScheduleParticipant> participants = scheduleParticipantRepository.findByScheduleId(scheduleId);

        assertNotNull(participants, "참석자 목록이 null입니다.");
        assertEquals(attendeeIds.size(), participants.size(), "추가된 참석자 수가 일치하지 않습니다.");
    }

    @DisplayName("일정 참석자를 업데이트한다.")
    @Test
    void testUpdateScheduleParticipant() {
        // given
        Long userId = 12L; // 요청 사용자 ID
        Long scheduleId = 11L; // 일정 ID
        Timestamp notificationTime = Timestamp.valueOf("2024-11-25T10:00:00.000+09:00");

        // 기존 참석자 추가
        List<Long> initialAttendees = List.of(4L, 5L);
        appScheduleParticipantService.addScheduleParticipant(userId, scheduleId, initialAttendees, notificationTime);

        // 새로운 참석자 목록
        List<Long> updatedAttendees = List.of(1L, 2L, 3L);

        // when
        appScheduleParticipantService.updateScheduleParticipant(userId, scheduleId, updatedAttendees);

        // then
        List<ScheduleParticipant> participants = scheduleParticipantRepository.findByScheduleId(scheduleId);

        assertNotNull(participants, "참석자 목록이 null입니다.");
        assertEquals(updatedAttendees.size(), participants.size(), "업데이트된 참석자 수가 일치하지 않습니다.");

        Set<Long> retrievedAttendeeIds = participants.stream()
                .map(ScheduleParticipant::getUserId)
                .collect(Collectors.toSet());
        assertTrue(retrievedAttendeeIds.containsAll(updatedAttendees), "업데이트된 참석자가 목록에 포함되지 않았습니다.");
    }
}