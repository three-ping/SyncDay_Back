package com.threeping.syncday.proj.command.aggregate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class NewProjDTO {
    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("proj_name")
    private String projName;

    @JsonProperty("start_time")
    Timestamp startTime;

    @JsonProperty("end_time")
    Timestamp endTime;

    @JsonProperty("vcs_type")
    String vcsType;

    @JsonProperty("vcs_proj_url")
    String vcsProjUrl;
}
