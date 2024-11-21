package com.threeping.syncday.projmember.query.repository;

import com.threeping.syncday.projmember.query.aggregate.ProjMember;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjMemberMapper {
    List<ProjMember> selectAllProjMembers();

    /* Todo: ProjId랑 UserId 인덱스순서로 쿼리 성능비교 */
    List<ProjMember> selectProjMembersByProjId(Long projId);

    List<ProjMember> selectProjInfosByUserId(Long userId);
}
