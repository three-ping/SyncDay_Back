package com.threeping.syncday.schedulerepeat.command.domain.service;

import com.threeping.syncday.schedulerepeat.command.aggregate.dto.CreateRepeatedScheduleDTO;

import java.util.Arrays;
import java.util.List;

public class ScheduleRepeatDomainServiceImpl implements ScheduleRepeatDomainService{

    @Override
    public void decodeRecurrencePattern(
            String recurrencePattern, CreateRepeatedScheduleDTO createRepeatedScheduleDTO) {
        List<Integer> recurrencePatternList = Arrays.stream(recurrencePattern.split("-"))
                .map(Integer::parseInt)
                .toList();

        createRepeatedScheduleDTO.setRecurrenceType();

    }
}
