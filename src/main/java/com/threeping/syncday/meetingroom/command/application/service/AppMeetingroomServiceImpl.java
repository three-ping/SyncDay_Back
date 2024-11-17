package com.threeping.syncday.meetingroom.command.application.service;

import com.threeping.syncday.common.exception.CommonException;
import com.threeping.syncday.common.exception.ErrorCode;
import com.threeping.syncday.meetingroom.command.aggregate.dto.MeetingroomDTO;
import com.threeping.syncday.meetingroom.command.aggregate.entity.Meetingroom;
import com.threeping.syncday.meetingroom.command.domain.repository.MeetingroomRepository;
import com.threeping.syncday.team.command.aggregate.entity.Team;
import com.threeping.syncday.team.command.domain.TeamRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class AppMeetingroomServiceImpl implements AppMeetingroomService {

    private final MeetingroomRepository meetingroomRepository;
    private final ModelMapper modelMapper;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    public AppMeetingroomServiceImpl(MeetingroomRepository meetingroomRepository
                                   , ModelMapper modelMapper) {
        this.meetingroomRepository = meetingroomRepository;
        this.modelMapper = modelMapper;
    }

//    @Transactional
//    @Override
//    public MeetingroomDTO addMeetingroom(MeetingroomDTO meetingroomDTO) {
//        Meetingroom meetingroom = modelMapper.map(meetingroomDTO, Meetingroom.class);
//        // 연관된 팀 설정 (필요한 경우)
//        if (meetingroomDTO.getTeamId() != null) {
//            Team team = teamRepository.findByTeamId(meetingroomDTO.getTeamId());
//            if (team == null) {
//                throw new CommonException(ErrorCode.INTERNAL_SERVER_ERROR);
//            }
//            meetingroom.setTeam(team);
//        }
//
//        log.info("meetingroom: {}", meetingroom);
//
//        Meetingroom addedMeetingroom = meetingroomRepository.save(meetingroom);
//        return modelMapper.map(addedMeetingroom, MeetingroomDTO.class);
//    }

    @Transactional
    @Override
    public MeetingroomDTO addMeetingroom(MeetingroomDTO meetingroomDTO) {
        Meetingroom meetingroom = modelMapper.map(meetingroomDTO, Meetingroom.class);
        // 연관된 팀 설정 (필요한 경우)
        if (meetingroomDTO.getTeamId() != null) {
            Team team = teamRepository.findByTeamId(meetingroomDTO.getTeamId());
            if (team == null) {
                throw new CommonException(ErrorCode.INTERNAL_SERVER_ERROR);
            }
            meetingroom.setTeam(team);
        }

        log.info("meetingroom: {}", meetingroom);

        Meetingroom addedMeetingroom = meetingroomRepository.save(meetingroom);

        // 응답 DTO 생성 시 teamId 설정
        MeetingroomDTO responseDTO = modelMapper.map(addedMeetingroom, MeetingroomDTO.class);
        if (addedMeetingroom.getTeam() != null) {
            responseDTO.setTeamId(addedMeetingroom.getTeam().getTeamId());
        }

        return responseDTO;
    }

    @Transactional
    @Override
    public MeetingroomDTO modifyMeetingroom(MeetingroomDTO meetingroomDTO) {
        // 회의실 조회
        Meetingroom existingMeetingroom = meetingroomRepository.findById(meetingroomDTO.getMeetingroomId())
                .orElseThrow(() -> new CommonException(ErrorCode.INTERNAL_SERVER_ERROR));

        // DTO의 값을 기존 엔티티에 업데이트
        existingMeetingroom.setMeetingroomPlace(meetingroomDTO.getMeetingroomPlace());
        existingMeetingroom.setMeetingroomName(meetingroomDTO.getMeetingroomName());
        existingMeetingroom.setMeetingroomCapacity(meetingroomDTO.getMeetingroomCapacity());

        // 연관된 팀 업데이트 (필요한 경우)
        if (meetingroomDTO.getTeamId() != null) {
            Team team = teamRepository.findByTeamId(meetingroomDTO.getTeamId());
            if (team == null) {
                throw new CommonException(ErrorCode.INTERNAL_SERVER_ERROR);
            }
            existingMeetingroom.setTeam(team);
        }

        log.info("existingMeetingroom: {}", existingMeetingroom);

        Meetingroom updatedMeetingroom = meetingroomRepository.save(existingMeetingroom);

        // 응답 DTO 생성 시 teamId 설정
        MeetingroomDTO responseDTO = modelMapper.map(updatedMeetingroom, MeetingroomDTO.class);
        if (updatedMeetingroom.getTeam() != null) {
            responseDTO.setTeamId(updatedMeetingroom.getTeam().getTeamId());
        }

        return responseDTO;
    }


//    @Transactional
//    @Override
//    public MeetingroomDTO deleteMeetingroom(Long meetingroomId) {
//        // meetingroomId로 회의실 조회
//        Meetingroom existingMeetingroom = meetingroomRepository.findByMeetingroomId(meetingroomId);
//        if (existingMeetingroom == null) {
//            throw new CommonException(ErrorCode.INTERNAL_SERVER_ERROR);
//        }
//        meetingroomRepository.delete(existingMeetingroom);
//
//        // 삭제된 엔티티를 DTO로 변환하여 반환 (필요에 따라)
//        return modelMapper.map(existingMeetingroom, MeetingroomDTO.class);
//    }
@Transactional
@Override
public MeetingroomDTO deleteMeetingroom(Long meetingroomId) {
    // meetingroomId로 회의실 조회 (팀을 명시적으로 페치)
    Meetingroom existingMeetingroom = meetingroomRepository.findByMeetingroomId(meetingroomId);
    if (existingMeetingroom == null) {
        throw new CommonException(ErrorCode.INTERNAL_SERVER_ERROR);
    }

    // 연관된 팀을 명시적으로 로드 (LAZY 로딩 이슈 방지)
    Team team = existingMeetingroom.getTeam();

    // 삭제 처리
    meetingroomRepository.delete(existingMeetingroom);

    // 삭제된 엔티티를 DTO로 변환하여 반환
    MeetingroomDTO responseDTO = modelMapper.map(existingMeetingroom, MeetingroomDTO.class);
    if (team != null) {
        responseDTO.setTeamId(team.getTeamId());
    }

    return responseDTO;
}


}
