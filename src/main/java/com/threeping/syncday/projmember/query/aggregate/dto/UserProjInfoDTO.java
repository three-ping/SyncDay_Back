package com.threeping.syncday.projmember.query.aggregate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class UserProjInfoDTO {
    @JsonProperty("user_id")
    Long userId;

    @JsonProperty("proj_member_infos")
    List<ProjAndMemberInfoDTO> projMemberInfos;
}
