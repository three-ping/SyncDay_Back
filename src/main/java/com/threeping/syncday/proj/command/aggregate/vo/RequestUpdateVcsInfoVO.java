package com.threeping.syncday.proj.command.aggregate.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.threeping.syncday.vcs.command.aggreagate.entity.VCSInstallation;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RequestUpdateVcsInfoVO {

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("proj_id")
    private Long projId;


    @JsonProperty("vcs_proj_url")
    private String vcsProjUrl;
}
