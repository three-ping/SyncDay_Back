package com.threeping.syncday.workspace.command.aggregate.vo;

import com.threeping.syncday.workspace.command.aggregate.entity.VcsType;
import lombok.Data;

import java.sql.Timestamp;

@Data

public class WorkspaceVO {

    private Long workspaceId;

    private String workspaceName;

    private Timestamp createdAt;

    private Byte progressStatus;

    private Long projId;

    private VcsType vcsType;

    private String vcsRepoUrl;
}
