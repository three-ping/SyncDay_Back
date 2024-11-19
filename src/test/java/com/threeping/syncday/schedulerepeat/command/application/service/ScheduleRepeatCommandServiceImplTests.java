package com.threeping.syncday.schedulerepeat.command.application.service;

import com.threeping.syncday.common.enumtype.MeetingStatus;
import com.threeping.syncday.common.enumtype.PublicStatus;
import com.threeping.syncday.schedulerepeat.command.aggregate.dto.CreateScheduleRepeatDTO;
import com.threeping.syncday.schedulerepeat.command.aggregate.entity.ScheduleRepeat;
import com.threeping.syncday.schedulerepeat.command.aggregate.enumtype.RecurrenceType;
import com.threeping.syncday.schedulerepeat.command.domain.repository.ScheduleRepeatRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class ScheduleRepeatCommandServiceImplTests {

    private final ScheduleRepeatCommandService scheduleRepeatCommandService;
    private final ScheduleRepeatRepository scheduleRepeatRepository;

    @Autowired
    ScheduleRepeatCommandServiceImplTests(ScheduleRepeatCommandService scheduleRepeatCommandService,
                                          ScheduleRepeatRepository scheduleRepeatRepository){
        this.scheduleRepeatCommandService = scheduleRepeatCommandService;
        this.scheduleRepeatRepository = scheduleRepeatRepository;
    }

    @Test
    @DisplayName("일정반복이 생성된다.")
    void createScheduleRepeatTest(){
        CreateScheduleRepeatDTO createScheduleRepeatDTO = new CreateScheduleRepeatDTO();
        createScheduleRepeatDTO.setUserId(1L);
        createScheduleRepeatDTO.setPublicStatus(PublicStatus.PUBLIC);
        createScheduleRepeatDTO.setMeetingStatus(MeetingStatus.ACTIVE);
        createScheduleRepeatDTO.setRecurrenceType(RecurrenceType.EVERY_WEEK_DAY);

        scheduleRepeatCommandService.createScheduleRepeat(createScheduleRepeatDTO);

        ScheduleRepeat savedScheduleRepeat = scheduleRepeatRepository.findAll()
                .stream()
                .filter(scheduleRepeat -> scheduleRepeat.getUserId().equals(1L))
                .findFirst()
                .orElseThrow(() -> new AssertionError("일정반복이 저장되지 않음"));

        assertEquals(1L,savedScheduleRepeat.getUserId(),"userId가 잘못 저장됨");
        assertEquals(PublicStatus.PUBLIC,savedScheduleRepeat.getPublicStatus(),"publicStatus가 잘못 저장됨");
        assertEquals(MeetingStatus.ACTIVE,savedScheduleRepeat.getMeetingStatus(),"meetingStatus가 잘못 저장됨");
        assertEquals(RecurrenceType.EVERY_MONTH_DAY,savedScheduleRepeat.getRecurrenceType(),"recurrencePattern이 잘못 저장됨");
    }

}