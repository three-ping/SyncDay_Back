package com.threeping.syncday.projmember.command.application.service;

import com.threeping.syncday.common.exception.CommonException;
import com.threeping.syncday.common.exception.ErrorCode;
import com.threeping.syncday.projmember.command.aggregate.entity.BookmarkStatus;
import com.threeping.syncday.projmember.command.aggregate.entity.ParticipationStatus;
import com.threeping.syncday.projmember.command.aggregate.entity.ProjMember;
import com.threeping.syncday.projmember.command.aggregate.entity.vo.ProjBookmarkVO;
import com.threeping.syncday.projmember.command.domain.repository.ProjMemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;

@Slf4j
@Service
public class AppProjMemberServiceImpl implements AppProjMemberService {

    private final ProjMemberRepository projMemberRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AppProjMemberServiceImpl(ProjMemberRepository projMemberRepository
                                    , ModelMapper modelMapper) {
        this.projMemberRepository = projMemberRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean addProjOwner(Long projId, Long userId) {
        ProjMember newMember = new ProjMember();
        newMember.setProjId(projId);
        newMember.setUserId(userId);
        newMember.setBookmarkStatus(BookmarkStatus.NONE);
        newMember.setParticipationStatus(ParticipationStatus.OWNER);
        log.debug("newMember: {}", newMember);
        ProjMember addedOwner = projMemberRepository.save(newMember);
        return addedOwner != null;
    }

    @Override
    public ProjBookmarkVO toggleProjBookmark(Long projMemberId) {

        ProjMember projMember = projMemberRepository.findById(projMemberId).orElse(null);
        if (projMember == null) {
            throw new CommonException(ErrorCode.PROJ_MEMBER_NOT_FOUND);
        }
        BookmarkStatus bookmarkStatus = projMember.getBookmarkStatus();
        if (bookmarkStatus == BookmarkStatus.NONE) {
            projMember.setBookmarkStatus(BookmarkStatus.BOOKMARKED);
        } else {
            projMember.setBookmarkStatus(BookmarkStatus.NONE);
        }
        ProjMember updatedMember = projMemberRepository.save(projMember);
        return new ProjBookmarkVO(updatedMember.getProjMemberId(), updatedMember.getBookmarkStatus());
    }
}
