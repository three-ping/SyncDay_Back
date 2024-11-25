package com.threeping.syncday.scheduleparticipant.query.service;

import com.threeping.syncday.scheduleparticipant.query.aggregate.ScheduleParticipantDTO;
import com.threeping.syncday.scheduleparticipant.query.repository.ScheduleParticipantMapper;
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
class ScheduleParticipantServiceTests {

    @Autowired
    ScheduleParticipantService scheduleParticipantService;

    @Autowired
    ScheduleParticipantMapper scheduleParticipantMapper;

    @DisplayName("현 시간부터 10분 후까지 알람 시각을 조회한다.")
    @Test
    void testGetParticipantsWithNotificationTimeInNext10Minutes() {
        // given
        Timestamp now = Timestamp.from(Instant.now());
        Timestamp tenMinutesLater = Timestamp.from(Instant.now().plusSeconds(600));

        // when
        List<ScheduleParticipantDTO> schedules =
                scheduleParticipantMapper.findParticipantsByNotificationTimeBetween(now, tenMinutesLater);

        // then
        // 결과 리스트가 null이 아니고 비어 있지 않음을 확인
        assertNotNull(schedules);

        // 리스트의 각 항목의 notificationTime이 now와 tenMinutesLater 사이에 있는지 확인
        for (ScheduleParticipantDTO schedule : schedules) {
            Timestamp notificationTime = schedule.getNotificationTime();
            assertTrue(notificationTime.after(now) && notificationTime.before(tenMinutesLater),
                    "The notificationTime should be between now and ten minutes later");
        }
    }
}