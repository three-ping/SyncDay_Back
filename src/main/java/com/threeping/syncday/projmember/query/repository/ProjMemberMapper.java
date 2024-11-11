package com.threeping.syncday.projmember.query.repository;

import com.threeping.syncday.projmember.query.aggregate.ProjMember;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjMemberMapper {
    List<ProjMember> selectAllProjMembers();
}
