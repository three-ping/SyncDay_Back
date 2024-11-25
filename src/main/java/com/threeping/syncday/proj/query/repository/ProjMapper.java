package com.threeping.syncday.proj.query.repository;

import com.threeping.syncday.proj.query.aggregate.Proj;
import com.threeping.syncday.proj.query.aggregate.dto.ProjAndWorkspaceDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjMapper {
    List<Proj> selectAllProjs();

    List<Proj> selectProjsByUserId(Long userId);

    Proj selectProjById(Long projId);

    List<ProjAndWorkspaceDTO> selectProjAndWorkspacesByUserId(Long userId);
}
