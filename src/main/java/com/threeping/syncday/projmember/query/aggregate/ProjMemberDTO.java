package com.threeping.syncday.projmember.query.aggregate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProjMemberDTO {

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("proj_id")
    private Long projId;

    @JsonProperty("bookmark_status")
    private String bookmarkStatus;

    @JsonProperty("participation_status")
    private String participationStatus;

    @JsonProperty("role")
    private String role;
}
