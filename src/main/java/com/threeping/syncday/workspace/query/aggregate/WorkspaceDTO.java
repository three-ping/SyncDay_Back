package com.threeping.syncday.workspace.query.aggregate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class WorkspaceDTO {

    @JsonProperty("workspace_id")
    private Long workspaceId;

    @JsonProperty("workspace_name")
    private String workspaceName;



    @JsonProperty("proj_id")
    private Long projId;


}
