package com.threeping.syncday.meetingroomreservation.query.aggregate;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class MeetingroomReservation {

    Long meetingroomReservationId;
    Timestamp meetingTime;
    Long meetingRoom;
    Long schedule;
}
