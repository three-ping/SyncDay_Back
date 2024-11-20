package com.threeping.syncday.projmember.query.service;
import com.threeping.syncday.projmember.query.aggregate.ProjMemberDTO;

import java.util.List;

public interface ProjMemberService {
    List<ProjMemberDTO> getAllProjMembers();

    List<ProjMemberDTO> getProjMembersByProjId(Long projId);
}
