package com.threeping.syncday.schedule.query.service;

import com.threeping.syncday.schedule.query.aggregate.Schedule;
import com.threeping.syncday.schedule.query.aggregate.ScheduleDTO;
import com.threeping.syncday.schedule.query.repository.ScheduleMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleMapper scheduleMapper;
    private final ModelMapper modelMapper;

    @Autowired
    public ScheduleServiceImpl(ScheduleMapper scheduleMapper, ModelMapper modelMapper) {
        this.scheduleMapper = scheduleMapper;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ScheduleDTO> getMySchedulesByUserId(Long userId) {
        List<Schedule> schedules = scheduleMapper.selectMySchedulesByUserId(userId);
        List<ScheduleDTO> scheduleDTOS =
                schedules.stream()
                        .map(schedule -> modelMapper.map(schedule, ScheduleDTO.class))
                        .collect(Collectors.toList());
        return scheduleDTOS;
    }

    @Override
    public List<ScheduleDTO> getOthersSchedulesBySearchUserId(Long searchUserId) {
        List<Schedule> schedules = scheduleMapper.selectOthersSchedulesBySearchUserId(searchUserId);
        List<ScheduleDTO> scheduleDTOS =
                schedules.stream()
                        .map(schedule -> modelMapper.map(schedule, ScheduleDTO.class))
                        .collect(Collectors.toList());
        return scheduleDTOS;
    }

    @Override
    public List<ScheduleDTO> getMyDetailSchedulesByUserIdAndScheduleId(Long userId, Long scheduleId) {
        List<Schedule> schedules = scheduleMapper.selectMyDetailSchedulesByUserIdAndScheduleId(userId, scheduleId);
        List<ScheduleDTO> scheduleDTOS =
                schedules.stream()
                        .map(schedule -> modelMapper.map(schedule, ScheduleDTO.class))
                        .collect(Collectors.toList());
        return scheduleDTOS;
    }
}
