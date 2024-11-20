package com.threeping.syncday.schedulerepeatparticipant.command.application.service;

import com.threeping.syncday.schedulerepeat.command.aggregate.dto.CreateScheduleRepeatDTO;
import com.threeping.syncday.schedulerepeatparticipant.command.aggregate.entity.ParticipationStatus;
import com.threeping.syncday.schedulerepeatparticipant.command.aggregate.entity.ScheduleRepeatParticipant;
import com.threeping.syncday.schedulerepeatparticipant.command.domain.repository.ScheduleRepeatParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleRepeatParticipantServiceImpl implements ScheduleRepeatParticipantService{

    private final ScheduleRepeatParticipantRepository scheduleRepeatParticipantRepository;

    @Autowired
    ScheduleRepeatParticipantServiceImpl(ScheduleRepeatParticipantRepository scheduleRepeatParticipantRepository){
        this.scheduleRepeatParticipantRepository = scheduleRepeatParticipantRepository;
    }

    @Override
    public void createScheduleRepeatParticipant(Long scheduleRepeatId,
                                                CreateScheduleRepeatDTO createScheduleRepeatDTO) {
        for (Long participantsId : createScheduleRepeatDTO.getParticipants()){
            ScheduleRepeatParticipant scheduleRepeatParticipant =
                    new ScheduleRepeatParticipant();
            scheduleRepeatParticipant.setUserId(participantsId);
            scheduleRepeatParticipant.setScheduleRepeatId(scheduleRepeatId);
            scheduleRepeatParticipant.setParticipationStatus(ParticipationStatus.PENDING);
            scheduleRepeatParticipantRepository.save(scheduleRepeatParticipant);
        }
    }
}
