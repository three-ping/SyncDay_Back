package com.threeping.syncday.proj.command.domain.service;

import com.threeping.syncday.proj.command.aggregate.vo.ProjVO;

public interface DomainProjService {
    public Boolean isValidMofificationRequest(ProjVO modifyProjVO);
}
