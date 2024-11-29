package com.threeping.syncday.projmember.query.repository;

import com.threeping.syncday.projmember.query.aggregate.ProjMember;
import com.threeping.syncday.projmember.query.aggregate.ProjMemberDTO;
import com.threeping.syncday.projmember.query.aggregate.dto.UserProjInfoDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProjMemberMapper {
    List<ProjMember> selectAllProjMembers();

    /* Todo: ProjId랑 UserId 인덱스순서로 쿼리 성능비교 */
    List<ProjMember> selectProjMembersByProjId(Long projId);

    List<UserProjInfoDTO> selectProjsByUserId(Long userId);

    ProjMember selectProjMemberByUserIdAndProjId(@Param("userId") Long userId, @Param("projId") Long projId);}
