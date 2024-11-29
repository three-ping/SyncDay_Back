package com.threeping.syncday.projmember.query.service;
import com.threeping.syncday.projmember.query.aggregate.ProjMemberDTO;
import com.threeping.syncday.projmember.query.aggregate.dto.UserProjInfoDTO;

import java.util.List;

public interface ProjMemberService {
    List<ProjMemberDTO> getAllProjMembers();

    List<ProjMemberDTO> getProjMembersByProjId(Long projId);

    List<UserProjInfoDTO> getProjsByUserId(Long userId);

}
