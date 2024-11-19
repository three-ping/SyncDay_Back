package com.threeping.syncday.proj.query.aggregate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;
@Data
public class ProjDTO {

    @JsonProperty("proj_id")
    Long projId;

    @JsonProperty("proj_name")
    String projName;

    @JsonProperty("start_time")
    Timestamp startTime;

    @JsonProperty("end_time")
    Timestamp endTime;

    @JsonProperty("created_at")
    Timestamp createdAt;

    @JsonProperty("updated_at")
    Byte progressStatus;

    @JsonProperty("vcs_type")
    String vcsType;

    @JsonProperty("vcs_proj_url")
    String vcsProjUrl;
}
