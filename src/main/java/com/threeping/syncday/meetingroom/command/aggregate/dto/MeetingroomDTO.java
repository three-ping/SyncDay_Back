package com.threeping.syncday.meetingroom.command.aggregate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MeetingroomDTO {

    private Long meetingroomId;
    private String meetingroomPlace;
    private String meetingroomName;
    private Integer meetingroomCapacity;
}
