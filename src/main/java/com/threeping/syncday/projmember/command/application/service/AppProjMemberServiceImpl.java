package com.threeping.syncday.projmember.command.application.service;

import com.threeping.syncday.common.exception.CommonException;
import com.threeping.syncday.common.exception.ErrorCode;
import com.threeping.syncday.proj.command.aggregate.dto.ProjDTO;
import com.threeping.syncday.projmember.command.aggregate.entity.BookmarkStatus;
import com.threeping.syncday.projmember.command.aggregate.entity.ParticipationStatus;
import com.threeping.syncday.projmember.command.aggregate.entity.ProjMember;
import com.threeping.syncday.projmember.command.aggregate.vo.UpdateProjRequest;
import com.threeping.syncday.projmember.command.aggregate.vo.UpdateProjResponse;
import com.threeping.syncday.projmember.command.aggregate.vo.UpdateWorkspaceRequest;
import com.threeping.syncday.projmember.command.aggregate.vo.UpdateWorkspaceResponse;
import com.threeping.syncday.projmember.command.domain.repository.ProjMemberRepository;
import com.threeping.syncday.projmember.command.infrastructure.service.InfraProjMemberService;
import com.threeping.syncday.workspace.command.aggregate.dto.WorkspaceDTO;
import com.threeping.syncday.workspace.command.aggregate.entity.VcsType;
import com.threeping.syncday.workspace.command.aggregate.vo.WorkspaceVO;
import com.threeping.syncday.workspace.command.application.controller.AppWorkspaceController;
import com.threeping.syncday.workspace.command.application.service.AppWorkspaceServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AppProjMemberServiceImpl implements AppProjMemberService {

    private final ProjMemberRepository projMemberRepository;
    private final ModelMapper modelMapper;
    private final InfraProjMemberService infraProjMemberService;
    private final AppWorkspaceController appWorkspaceController;
    private final AppWorkspaceServiceImpl appWorkspaceService;

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
    public BookmarkStatus updateProjBookmark(Long projmemberId) {
        ProjMember member = projMemberRepository.findById(projmemberId).orElse(null);
        if (member == null) {
            throw new CommonException(ErrorCode.PROJ_MEMBER_NOT_FOUND);
        }
        if (member.getBookmarkStatus().equals(BookmarkStatus.BOOKMARKED)) {
            member.setBookmarkStatus(BookmarkStatus.NONE);
        }
        else {
            member.setBookmarkStatus(BookmarkStatus.BOOKMARKED);
        }
        ProjMember updatedMember = projMemberRepository.save(member);
        return updatedMember.getBookmarkStatus();
    }

    @Override
    public UpdateProjResponse addProj(UpdateProjRequest req) {
        ProjDTO proj = infraProjMemberService.requestAddProj(req);
        ProjMember member = new ProjMember();
        member.setUserId(req.userId());
        member.setBookmarkStatus(BookmarkStatus.NONE);
        member.setProjId(proj.getProjId());
        member.setParticipationStatus(ParticipationStatus.OWNER);
        return new UpdateProjResponse(proj.getProjId(), member.getProjMemberId(), proj.getProjName(), proj.getStartTime(), proj.getEndTime(),proj.getVcsType(), proj.getVcsProjUrl(), proj.getGithubInstallationId());
    }

    @Override
    public UpdateProjResponse updateProj(UpdateProjRequest req) {
        ProjMember member = projMemberRepository.findById(req.projMemberId()).orElse(null);
        if (member == null) {
            throw new CommonException(ErrorCode.PROJ_MEMBER_NOT_FOUND);
        } else if(!member.getParticipationStatus().equals(ParticipationStatus.OWNER)) {
            throw new CommonException(ErrorCode.PROJ_INVALID_REQUEST);
        }
        ProjDTO proj = infraProjMemberService.requestUpdateProj(req);

        return new UpdateProjResponse(proj.getProjId(),member.getProjMemberId(), proj.getProjName(),proj.getStartTime(), proj.getEndTime(), proj.getVcsType(), proj.getVcsProjUrl() , proj.getGithubInstallationId());
    }

    @Override
    public WorkspaceDTO updateWorkspace(UpdateWorkspaceRequest updateWorkspaceRequest) {
        ProjMember member = projMemberRepository.findById(updateWorkspaceRequest.projMemberId()).orElse(null);
        if (member == null) {
            throw new CommonException(ErrorCode.PROJ_MEMBER_NOT_FOUND);
        }
        else if (!member.getParticipationStatus().equals(ParticipationStatus.OWNER)) {
            throw new CommonException(ErrorCode.PROJ_INVALID_REQUEST);
        }

        WorkspaceVO vo = new WorkspaceVO(updateWorkspaceRequest.workspaceId(), updateWorkspaceRequest.workspaceName(), VcsType.GITHUB, updateWorkspaceRequest.vcsRepoName(), updateWorkspaceRequest.vcsRepoUrl());
        log.info("vo: {}", vo);
        WorkspaceDTO updatedWorkspace = infraProjMemberService.requestUpdateWorkspace(vo);
        return updatedWorkspace;
    }
}
