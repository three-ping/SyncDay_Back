package com.threeping.syncday.proj.query.repository;

import com.threeping.syncday.proj.query.aggregate.Proj;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjMapper {
    List<Proj> selectAllProjs();
}
