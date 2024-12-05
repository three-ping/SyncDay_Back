package com.threeping.syncday.proj.command.aggregate.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.threeping.syncday.vcs.command.aggreagate.entity.VCSInstallation;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ProjVO {
    /* add, modify 전용 VO */

    @JsonProperty("proj_id")
    private Long projId;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("proj_name")
    private String projName;

    @JsonProperty("start_time")
    Timestamp startTime;

    @JsonProperty("end_time")
    Timestamp endTime;


    @JsonProperty("vcs_proj_url")
    String vcsProjUrl;
}
