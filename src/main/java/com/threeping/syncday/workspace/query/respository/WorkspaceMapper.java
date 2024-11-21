package com.threeping.syncday.workspace.query.respository;

import com.threeping.syncday.workspace.query.aggregate.Workspace;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WorkspaceMapper {

    List<Workspace> selectAllWorkSpaces();

    List<Workspace> selectWorkspacesByProjId(Long projId);
}
