package com.threeping.syncday.scheduleparticipant.command.aggregate.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "TBL_USER_SCHEDULE")
@IdClass(ScheduleParticipantPK.class)
public class ScheduleParticipant {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Id
    @Column(name = "schedule_id")
    private Long scheduleId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ParticipationStatus participationStatus = ParticipationStatus.PENDING;

    @Column(name = "notification_time")
    private Timestamp notificationTime;
}
