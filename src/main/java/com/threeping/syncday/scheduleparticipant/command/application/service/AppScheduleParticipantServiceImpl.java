package com.threeping.syncday.scheduleparticipant.command.application.service;

import com.threeping.syncday.scheduleparticipant.command.aggregate.entity.ScheduleParticipant;
import com.threeping.syncday.scheduleparticipant.command.domain.repository.ScheduleParticipantRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AppScheduleParticipantServiceImpl implements AppScheduleParticipantService{

    private final ScheduleParticipantRepository scheduleParticipantRepository;

    @Autowired
    public AppScheduleParticipantServiceImpl(ScheduleParticipantRepository scheduleParticipantRepository) {
        this.scheduleParticipantRepository = scheduleParticipantRepository;
    }

    @Transactional
    @Override
    public void addScheduleParticipant(Long userId, Long scheduleId, List<Long> attendeeIds) {

        // 참석자 리스트에 있는 attendeeId만 추가
        for (Long attendeeId: attendeeIds) {
            ScheduleParticipant scheduleParticipant = new ScheduleParticipant();

            scheduleParticipant.setUserId(attendeeId);
            scheduleParticipant.setScheduleId(scheduleId);

            scheduleParticipantRepository.save(scheduleParticipant);
        }
    }

    @Transactional
    @Override
    public void updateScheduleParticipant(Long userId, Long scheduleId, List<Long> attendeeIds) {

        // 기존 참석자 목록 조회
        List<ScheduleParticipant> currentParticipants = scheduleParticipantRepository.findByScheduleId(scheduleId);

        // 기존 참석자 Id 목록
        Set<Long> currentAttendeeIds = currentParticipants.stream()
                                                        .map(ScheduleParticipant::getUserId)
                                                        .collect(Collectors.toSet());

        Set<Long> newAttendeeIds = new HashSet<>(attendeeIds);

        // 기존에는 있지만 새 목록에는 없는 참석자(삭제해야할 참석자)
        Set<Long> attendeesToRemove = currentAttendeeIds.stream()
                                                    .filter(id -> !newAttendeeIds.contains(id))
                                                    .collect(Collectors.toSet());

        // 기존에는 없지만 새 목록에는 있는 참석자(추가해야할 참석자)
        Set<Long> attendeesToAdd = newAttendeeIds.stream()
                                                .filter(id -> !currentAttendeeIds.contains(id))
                                                .collect(Collectors.toSet());

        // 삭제처리
        for (Long id: attendeesToRemove) {
            scheduleParticipantRepository.deleteByScheduleIdAndUserId(scheduleId, id);
        }

        // 추가처리
        for (Long id: attendeesToAdd) {
            ScheduleParticipant newScheduleParticipant = new ScheduleParticipant();
            newScheduleParticipant.setScheduleId(scheduleId);
            newScheduleParticipant.setUserId(id);

            scheduleParticipantRepository.save(newScheduleParticipant);
        }
    }
}
