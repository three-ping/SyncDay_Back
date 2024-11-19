package com.threeping.syncday.proj.query.aggregate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.threeping.syncday.projmember.query.aggregate.ProjMember;
import lombok.Data;

import java.util.List;

@Data
public class ProjAndMemberDTO {

    @JsonProperty("proj_id")
    private Long projId;

    @JsonProperty("proj_members")
    private List<ProjMember> projMembers;


}
