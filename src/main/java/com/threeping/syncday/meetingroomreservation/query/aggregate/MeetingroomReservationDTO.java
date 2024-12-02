package com.threeping.syncday.meetingroomreservation.query.aggregate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class MeetingroomReservationDTO {

   @JsonProperty("meetingroom_reservation_id")
    Long meetingroomReservationId;

   @JsonProperty("meeting_time")
   Timestamp meetingTime;

    @JsonProperty("title")
    String title;

    @JsonProperty("end_time")
    Timestamp endTime;

    @JsonProperty("start_time")
    Timestamp startTime;

   @JsonProperty("meetingroom_id")
    Long meetingRoom;

   @JsonProperty("schedule_id")
    Long schedule;
}
