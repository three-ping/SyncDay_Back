package com.threeping.syncday.meetingroomreservation.command.application.service;

import com.threeping.syncday.common.exception.CommonException;
import com.threeping.syncday.common.exception.ErrorCode;
import com.threeping.syncday.meetingroomreservation.command.aggregate.dto.MeetingroomReservationDTO;
import com.threeping.syncday.meetingroomreservation.command.aggregate.entity.MeetingroomReservation;
import com.threeping.syncday.meetingroomreservation.command.domain.repository.MeetingroomReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
class AppMeetingroomReservationServiceTests {

    @Autowired
    AppMeetingroomReservationService appMeetingroomReservationService;

    @Autowired
    MeetingroomReservationRepository meetingroomReservationRepository;

    @DisplayName("회의실 예약 생성 테스트")
    @Test
    void testddMeetingroomReservation() {

        // Given
        MeetingroomReservationDTO dto = new MeetingroomReservationDTO();
        dto.setTitle("Test Meeting");
        dto.setContent("Test Content");
        dto.setStartTime(Timestamp.from(Instant.now()));
        dto.setEndTime(Timestamp.from(Instant.now().plusSeconds(600))); // 10분 후
        dto.setMeetingroomId(1L);
        dto.setUserId(1L);

        // When
        List<MeetingroomReservationDTO> createdReservations = appMeetingroomReservationService.addMeetingroomReservation(dto);

        // Then
        assertNotNull(createdReservations);
        assertFalse(createdReservations.isEmpty(), "Reservations should not be empty");
        createdReservations.forEach(reservation -> {
            assertNotNull(reservation.getMeetingroomReservationId());
            assertEquals(dto.getMeetingroomId(), reservation.getMeetingroomId());
            assertEquals(dto.getTitle(), reservation.getTitle());
            assertEquals(dto.getContent(), reservation.getContent());
        });
        log.info("Created reservations: {}", createdReservations);
    }

    @DisplayName("회의실 예약 삭제 테스트")
    @Test
    void testDeleteMeetingroomReservation() {

        // Given
        MeetingroomReservationDTO dto = new MeetingroomReservationDTO();
        dto.setTitle("Test Meeting");
        dto.setContent("Test Content");
        dto.setStartTime(Timestamp.from(Instant.now()));
        dto.setEndTime(Timestamp.from(Instant.now().plusSeconds(600))); // 10분 후
        dto.setMeetingroomId(1L);
        dto.setUserId(1L);

        List<MeetingroomReservationDTO> createdReservations = appMeetingroomReservationService.addMeetingroomReservation(dto);
        assertFalse(createdReservations.isEmpty(), "Reservations should not be empty");
        Long reservationIdToCheck = createdReservations.get(0).getMeetingroomReservationId();

        // 조회하여 ScheduleId 가져오기
        MeetingroomReservation reservation = meetingroomReservationRepository.findById(reservationIdToCheck)
                .orElseThrow(() -> new CommonException(ErrorCode.INTERNAL_SERVER_ERROR));
        Long scheduleIdToDelete = reservation.getSchedule();

        // When
        appMeetingroomReservationService.deleteMeetingroomReservationByScheduleId(scheduleIdToDelete);

        // Then
        boolean isDeleted = meetingroomReservationRepository.findAll().stream()
                .noneMatch(r -> r.getSchedule().equals(scheduleIdToDelete));
        assertTrue(isDeleted, "Reservations with the given scheduleId should be deleted");
    }
}