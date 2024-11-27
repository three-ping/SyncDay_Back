package com.threeping.syncday.projmember.command.application.service;

import com.threeping.syncday.common.ResponseDTO;
import com.threeping.syncday.projmember.command.aggregate.entity.vo.ProjBookmarkVO;

public interface AppProjMemberService {
    Boolean addProjOwner(Long projId, Long userId);

    ProjBookmarkVO toggleProjBookmark(Long projMemberId);
}
