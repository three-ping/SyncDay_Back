package com.threeping.syncday.projmember.command.aggregate.entity;

import jakarta.persistence.Column;
import lombok.Data;


import java.io.Serializable;

@Data
public class ProjMemberPK implements Serializable {

    @Column(name="proj_id")
    private Long projId;

    @Column(name="user_id")
    private Long userId;


}
