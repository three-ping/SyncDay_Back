package com.threeping.syncday.workspace.query.service;


import com.threeping.syncday.workspace.query.aggregate.Workspace;
import com.threeping.syncday.workspace.query.aggregate.WorkspaceDTO;
import com.threeping.syncday.workspace.query.aggregate.WorkspaceInfoDTO;
import com.threeping.syncday.workspace.query.respository.WorkspaceMapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class WorkspaceServiceImpl implements WorkspaceService {

    private final WorkspaceMapper workspaceMapper;
    private final ModelMapper modelMapper;

    @Autowired
    public WorkspaceServiceImpl(WorkspaceMapper workspaceMapper
            , ModelMapper modelMapper) {
        this.workspaceMapper = workspaceMapper;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<com.threeping.syncday.workspace.query.aggregate.WorkspaceDTO> getAllWorkspaces() {
        List<Workspace> workspaces = workspaceMapper.selectAllWorkSpaces();
        List<com.threeping.syncday.workspace.query.aggregate.WorkspaceDTO> workspaceDTOs = workspaces.stream().map(workspace -> modelMapper.map(workspace, com.threeping.syncday.workspace.query.aggregate.WorkspaceDTO.class)).collect(Collectors.toList());
        return workspaceDTOs;
    }

    @Override
    public List<WorkspaceDTO> getWorkspacesByProjId(Long projId) {
       List<Workspace> workspaces= workspaceMapper.selectWorkspacesByProjId(projId);
       return workspaces.stream().map(workspace -> modelMapper.map(workspace, WorkspaceDTO.class)).collect(Collectors.toList());
    }

    @Override
    public WorkspaceInfoDTO getWorkspaceInfo(Long workspaceId) {
        return workspaceMapper.selectWorkspaceById(workspaceId);
    }
}
