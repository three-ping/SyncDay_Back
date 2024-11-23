package com.threeping.syncday.workspace.command.application.service;

import com.threeping.syncday.workspace.command.aggregate.vo.WorkspaceVO;

public interface AppWorkspaceService {
    WorkspaceVO addWorkspace(WorkspaceVO newWorkspace);

    WorkspaceVO modifyWorkspace(WorkspaceVO workspaceVO);

    WorkspaceVO deleteWorkspace(Long workspaceId);
}
