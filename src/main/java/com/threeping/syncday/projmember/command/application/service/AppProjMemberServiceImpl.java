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
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Book;

@Slf4j
@Service
@Transactional
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
    public Boolean addProjBookmark(Long userId, Long projId) {
         try {
            ProjMember member = projMemberRepository.findByUserIdAndProjId(userId, projId);
            if (member == null) {
                throw new CommonException(ErrorCode.PROJ_MEMBER_NOT_FOUND);
            }
            ProjMember updatedMember = projMemberRepository.save(member);
            return updatedMember.getBookmarkStatus().equals(BookmarkStatus.BOOKMARKED);
        } catch(Exception e){
     throw new CommonException(ErrorCode.INTERNAL_SERVER_ERROR);
         }
    }

    @Override
    public Boolean removeProjBookmark(Long userId, Long projId) {
        try {
            // Find existing project member record
            ProjMember member = projMemberRepository.findByUserIdAndProjId(userId, projId);

            // Update bookmark status
            member.setBookmarkStatus(BookmarkStatus.NONE);
            log.debug("Removing bookmark for member: {}", member);

            // Save the updated member
            ProjMember updatedMember = projMemberRepository.save(member);
            return updatedMember.getBookmarkStatus().equals(BookmarkStatus.NONE);
        } catch (CommonException ce) {
            throw ce;
        } catch (Exception e) {
            log.error("Error removing project bookmark: ", e);
            throw new CommonException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }


}
