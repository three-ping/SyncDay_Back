package com.threeping.syncday.workspace.query.respository;

import com.threeping.syncday.workspace.query.aggregate.CardboardVO;
import com.threeping.syncday.workspace.query.aggregate.CardVO;
import com.threeping.syncday.workspace.query.aggregate.Workspace;
import com.threeping.syncday.workspace.query.aggregate.WorkspaceInfoDTO;
import com.threeping.syncday.workspace.query.dto.WorkspaceQueryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WorkspaceMapper {

    List<Workspace> selectAllWorkSpaces();
    WorkspaceQueryDTO findWorkspaceById(Long workspaceId);
    List<Workspace> selectWorkspacesByProjId(Long projId);
    WorkspaceInfoDTO selectWorkspaceById(@Param("workspaceId") Long workspaceId);
    List<WorkspaceQueryDTO> findAllWorkspaces();
}
