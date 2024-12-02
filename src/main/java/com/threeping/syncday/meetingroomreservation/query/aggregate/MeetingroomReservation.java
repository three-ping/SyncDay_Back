package com.threeping.syncday.meetingroomreservation.query.aggregate;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class MeetingroomReservation {

    Long meetingroomReservationId;
    Timestamp meetingTime;
    Long meetingRoom;
    Long schedule;

    // 추가: title, startTime, endTime 속성 및 getter/setter
     String title;
     Timestamp startTime;
     Timestamp endTime;
}
