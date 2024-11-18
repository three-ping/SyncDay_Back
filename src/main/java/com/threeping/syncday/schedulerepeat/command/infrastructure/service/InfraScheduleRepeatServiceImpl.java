package com.threeping.syncday.schedulerepeat.command.infrastructure.service;

import com.threeping.syncday.schedule.command.aggregate.dto.ScheduleDTO;
import com.threeping.syncday.schedule.command.application.service.AppScheduleService;
import com.threeping.syncday.schedulerepeat.command.aggregate.dto.CreateRepeatedScheduleDTO;
import com.threeping.syncday.schedulerepeat.command.aggregate.dto.CreateScheduleRepeatDTO;
import com.threeping.syncday.schedulerepeat.command.aggregate.vo.ScheduleDurationVO;
import com.threeping.syncday.schedulerepeatparticipant.command.application.service.ScheduleRepeatParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class InfraScheduleRepeatServiceImpl implements InfraScheduleRepeatService {

    private final AppScheduleService appScheduleService;
    private final ScheduleRepeatParticipantService scheduleRepeatParticipantService;

    @Autowired
    InfraScheduleRepeatServiceImpl(AppScheduleService appScheduleService,
                                   ScheduleRepeatParticipantService scheduleRepeatParticipantService){
        this.appScheduleService = appScheduleService;
        this.scheduleRepeatParticipantService = scheduleRepeatParticipantService;
    }


    @Override
    public void createSchedule(ScheduleDurationVO scheduleDuration,
                               CreateRepeatedScheduleDTO createRepeatedScheduleDTO,
                               Long repeatOrder) {
        ScheduleDTO newScheduleDTO = makeNewScheduleDTO(scheduleDuration,createRepeatedScheduleDTO,repeatOrder);

        appScheduleService.addSchedule(newScheduleDTO);
    }

    @Override
    public void createScheduleRepeatParticipants(Long repeatId, CreateScheduleRepeatDTO createScheduleRepeatDTO) {
        scheduleRepeatParticipantService.createScheduleRepeatParticipant(repeatId,createScheduleRepeatDTO);
    }

    private ScheduleDTO makeNewScheduleDTO(ScheduleDurationVO scheduleDuration,
                                           CreateRepeatedScheduleDTO createRepeatedScheduleDTO,
                                           Long repeatOrder) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setUserId(createRepeatedScheduleDTO.getUserId());
        scheduleDTO.setTitle(createRepeatedScheduleDTO.getTitle());
        scheduleDTO.setContent(createRepeatedScheduleDTO.getContent());
        scheduleDTO.setStartTime(scheduleDuration.start());
        scheduleDTO.setEndTime(scheduleDuration.end());
        scheduleDTO.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        scheduleDTO.setPublicStatus(createRepeatedScheduleDTO.getPublicStatus());
        scheduleDTO.setScheduleRepeatId(createRepeatedScheduleDTO.getScheduleRepeatId());
        scheduleDTO.setRepeatOrder(repeatOrder);
        scheduleDTO.setMeetingStatus(createRepeatedScheduleDTO.getMeetingStatus());
        scheduleDTO.setAttendeeIds(createRepeatedScheduleDTO.getParticipants());
        return scheduleDTO;
    }
}
