package com.threeping.syncday.workspace.query.service;


import com.threeping.syncday.workspace.query.aggregate.WorkspaceDTO;
import com.threeping.syncday.workspace.query.aggregate.WorkspaceInfoDTO;

import java.util.List;

public interface WorkspaceService {
    List<com.threeping. syncday.workspace.query.aggregate.WorkspaceDTO> getAllWorkspaces();

    List<WorkspaceDTO> getWorkspacesByProjId(Long projId);

    public WorkspaceInfoDTO getWorkspaceInfo(Long workspaceId);

}
