package com.threeping.syncday.workspace.query.service;


import com.threeping.syncday.workspace.query.aggregate.WorkspaceDTO;

import java.util.List;

public interface WorkspaceService {
    List<com.threeping.syncday.workspace.query.aggregate.WorkspaceDTO> getAllWorkspaces();

    List<WorkspaceDTO> getWorkspacesByProjId(Long projId);
}
