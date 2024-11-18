package com.threeping.syncday.meetingroom.query.aggregate;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class MeetingroomDTO {

    @JsonProperty("meetingroom_id")
    Long meetingroomId;

    @JsonProperty("meetingroom_place")
    String meetingroomPlace;

    @JsonProperty("meetingroom_name")
    String meetingroomName;

    @JsonProperty("meetingroom_capacity")
    Integer meetingroomCapacity;

}
