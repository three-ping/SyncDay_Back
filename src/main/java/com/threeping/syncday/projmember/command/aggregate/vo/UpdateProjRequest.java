package com.threeping.syncday.projmember.command.aggregate.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.threeping.syncday.proj.command.aggregate.entity.VcsType;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class UpdateProjRequest {

    @JsonProperty("proj_id")
     Long projId;
    @JsonProperty("user_id")
     Long userId;

    @JsonProperty("proj_name")
     String projName;

    @JsonProperty("vcs_type")
     VcsType vcsType;

    @JsonProperty("vcs_proj_url")
     String vcsProjUrl;
    @JsonProperty("start_time")
     Timestamp startTime;

    @JsonProperty("end_time")
     Timestamp endTime;

    @JsonProperty("vcs_installation_id")
    Long vcsInstallationId;


}
