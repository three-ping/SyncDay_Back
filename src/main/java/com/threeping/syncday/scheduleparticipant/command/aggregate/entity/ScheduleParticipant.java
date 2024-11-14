package com.threeping.syncday.scheduleparticipant.command.aggregate.entity;

import com.threeping.syncday.common.enumtype.ParticipationStatus;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TBL_SCHEDULE_PARTICIPANT")
@IdClass(ScheduleParticipantPK.class)
public class ScheduleParticipant {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Id
    @Column(name = "schedule_id")
    private Long scheduleId;

    @Enumerated(EnumType.STRING)
    @Column(name = "participation_status")
    private ParticipationStatus participationStatus = ParticipationStatus.PENDING;
}
