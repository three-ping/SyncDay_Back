package com.threeping.syncday.schedulerepeatparticipant.command.aggregate.entity;

import jakarta.persistence.Column;

import java.io.Serializable;

public class ScheduleRepeatParticipantPK implements Serializable {
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "schedule_repeat_id")
    private Long scheduleRepeatId;
}
