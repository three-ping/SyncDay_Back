package com.threeping.syncday.schedule.query.service;

import com.threeping.syncday.schedule.query.aggregate.ScheduleDTO;
import com.threeping.syncday.schedule.query.aggregate.ScheduleDetailDTO;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
class ScheduleServiceTests {

    @Autowired
    ScheduleService scheduleService;

    @DisplayName("나의 일정을 조회한다.")
    @Test
    void testGetMySchedulesByUserId() {
        // given
        Long userId = 1L;

        // when
        List<ScheduleDTO> mySchedules = scheduleService.getMySchedulesByUserId(userId);

        // then
        assertNotNull(mySchedules, "일정 목록이 null 입니다.");
        assertFalse(mySchedules.isEmpty(), "조회된 일정이 없습니다.");
    }

    @DisplayName("타인의 일정을 조회한다.")
    @Test
    void testGetOthersSchedulesBySearchUserId() {
        // given
        Long searchUserId = 2L;

        // when
        List<ScheduleDTO> publicSchedules = scheduleService.getOthersSchedulesBySearchUserId(searchUserId);

        // then
        assertNotNull(publicSchedules, "공개 일정 목록이 null 입니다.");
        assertFalse(publicSchedules.isEmpty(), "조회된 공개 일정이 없습니다.");
    }

    @DisplayName("나의 일정을 상세 조회한다.")
    @Test
    void testGetMyDetailSchedulesByUserIdAndScheduleId() {
        // given
        Long userId = 1L;
        Long scheduleId = 1L;

        // when
        List<ScheduleDetailDTO> detailSchedules = scheduleService.getMyDetailSchedulesByUserIdAndScheduleId(userId, scheduleId);

        // then
        assertNotNull(detailSchedules, "상세 일정 목록이 null 입니다.");
        assertFalse(detailSchedules.isEmpty(), "조회된 상세 일정이 없습니다.");
    }
}