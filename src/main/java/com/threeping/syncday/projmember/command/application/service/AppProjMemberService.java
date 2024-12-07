package com.threeping.syncday.projmember.command.application.service;

import com.threeping.syncday.common.ResponseDTO;
import com.threeping.syncday.projmember.command.aggregate.entity.BookmarkStatus;
import com.threeping.syncday.projmember.command.aggregate.entity.ProjMember;
import com.threeping.syncday.projmember.command.aggregate.vo.UpdateProjRequest;

public interface AppProjMemberService {
    Boolean addProjOwner(Long projId, Long userId);

    BookmarkStatus updateProjBookmark(Long projMemberId);

    ProjMember addProj(UpdateProjRequest req);
}
