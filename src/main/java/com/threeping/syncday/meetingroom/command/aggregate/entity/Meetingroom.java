package com.threeping.syncday.meetingroom.command.aggregate.entity;

import com.threeping.syncday.team.command.aggregate.entity.Team;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="TBL_MEETINGROOM")
@Data
public class Meetingroom {

    @Id
    @Column(name="meetingroom_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long meetingroomId;

    @Column(name="meetingroom_place")
    // 장소가 어딘지 ((ex) 한화시스템, 모모빌딩 등)
    private String meetingroomPlace;

    // 회의실 이름 ((ex)RM101)
    @Column(name="meetingroom_name")
    private String meetingroomName;

    // 회의실 수용인원 ((ex) 33명)
    @Column(name="meetingroom_capacity")
    private Integer meetingroomCapacity;

    // 팀은 여러개의 회의실 예약 가능
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="team_id", nullable = false)
    private Team team;
}
