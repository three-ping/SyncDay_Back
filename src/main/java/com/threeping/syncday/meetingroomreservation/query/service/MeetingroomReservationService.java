package com.threeping.syncday.meetingroomreservation.query.service;

import com.threeping.syncday.meetingroomreservation.query.aggregate.MeetingroomReservationDTO;

import java.util.List;

public interface MeetingroomReservationService {

    List<MeetingroomReservationDTO> getAllMeetingroomReservations();
}
