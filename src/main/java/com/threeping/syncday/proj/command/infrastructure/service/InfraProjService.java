package com.threeping.syncday.proj.command.infrastructure.service;



public interface InfraProjService {

    Boolean requestAddProjOwner(Long projId, Long userId);

    String requestParticipationStatus(Long userId, Long projId);
}
