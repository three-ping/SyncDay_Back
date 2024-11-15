package com.threeping.syncday.team.command.aggregate.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="TBL_TEAM")
@Data
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="team_id")
    private Long teamId;

    @Column(name="team_name", nullable = false)
    private String teamName;
}
