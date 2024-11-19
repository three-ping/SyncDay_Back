package com.threeping.syncday.meetingroomreservation.command.application.service;

import com.threeping.syncday.meetingroomreservation.command.aggregate.dto.MeetingroomReservationDTO;

import java.util.List;

public interface AppMeetingroomReservationService {

    List<MeetingroomReservationDTO> addMeetingroomReservation(MeetingroomReservationDTO meetingroomReservationDTO);

    void deleteMeetingroomReservationByScheduleId(Long scheduleId);
}
