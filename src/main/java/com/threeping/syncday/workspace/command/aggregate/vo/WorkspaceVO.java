package com.threeping.syncday.workspace.command.aggregate.vo;

import com.threeping.syncday.workspace.command.aggregate.entity.VcsType;
import lombok.Data;

import java.sql.Timestamp;

@Data

public class WorkspaceVO {
    /* Todo: 유효성 검사를 위해 유저아이디를 추가하여 VO로 생성 */
    private Long userId;

    private Long workspaceId;

    private String workspaceName;

    private Timestamp createdAt;

    private Byte progressStatus;

    private Long projId;

    private VcsType vcsType;

    private String vcsRepoUrl;
}
