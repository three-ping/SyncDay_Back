package com.threeping.syncday.meetingroom.query.aggregate;

import lombok.Data;

@Data
public class Meetingroom {

    Long meetingroomId;
    String meetingroomPlace;
    String meetingroomName;
    Integer meetingroomCapacity;
}
