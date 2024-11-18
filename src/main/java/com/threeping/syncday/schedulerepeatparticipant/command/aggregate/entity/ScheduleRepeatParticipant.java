package com.threeping.syncday.schedulerepeatparticipant.command.aggregate.entity;

import com.threeping.syncday.common.enumtype.ParticipationStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TBL_SCHEDULE_REPEAT_PARTICIPANT")
@IdClass(ScheduleRepeatParticipantPK.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ScheduleRepeatParticipant {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Id
    @Column(name = "schedule_repeat_id")
    private Long scheduleRepeatId;

    @Enumerated(EnumType.STRING)
    @Column(name = "participation_status")
    private ParticipationStatus participationStatus = ParticipationStatus.PENDING;
}
