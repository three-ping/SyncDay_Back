package com.threeping.syncday.scheduleparticipant.command.aggregate.entity;

import jakarta.persistence.Column;
import lombok.Data;

import java.io.Serializable;

@Data
public class ScheduleParticipantPK implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "schedule_id")
    private Long scheduleId;
}
