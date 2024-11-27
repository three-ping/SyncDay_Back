package com.threeping.syncday.cardboard.command.aggreate.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.threeping.syncday.cardboard.command.aggreate.entity.VcsType;
import lombok.Data;

@Data
public class AddCardboardVO {

    @JsonProperty("title")
    private String title;


    @JsonProperty("vcs_type")
    private VcsType vcsType;

    @JsonProperty("vcs_milestone_url")
    private String vcsMilestoneUrl;


    @JsonProperty("workspace_id")
    private Long workspaceId;
}
