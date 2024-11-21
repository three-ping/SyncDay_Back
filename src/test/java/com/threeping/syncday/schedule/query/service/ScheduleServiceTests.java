package com.threeping.syncday.schedule.query.service;

import com.threeping.syncday.schedule.query.aggregate.ScheduleDTO;
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
        Long userId = 1L;

        // when
        List<ScheduleDTO> mySchedules = scheduleService.getMySchedulesByUserId(userId);

        // then
        assertNotNull(mySchedules);
        assertFalse(mySchedules.isEmpty(), "조회된 일정이 없습니다.");

        mySchedules.forEach(schedule -> System.out.println(schedule.toString()));
    }

    @DisplayName("타인의 일정을 조회한다.")
    @Test
    void testGetOthersSchedulesBySearchUserId() {
        Long searchUserId = 1L;

        // when
        List<ScheduleDTO> publicSchedules = scheduleService.getOthersSchedulesBySearchUserId(searchUserId);

        // then
        assertNotNull(publicSchedules);
        assertFalse(publicSchedules.isEmpty(), "조회된 공개 일정이 없습니다.");

        publicSchedules.forEach(schedule -> System.out.println(schedule.toString()));
    }
}