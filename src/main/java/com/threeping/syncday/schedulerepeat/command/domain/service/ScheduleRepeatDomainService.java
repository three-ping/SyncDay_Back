package com.threeping.syncday.schedulerepeat.command.domain.service;

import com.threeping.syncday.schedulerepeat.command.aggregate.dto.RepeatDTO;
import com.threeping.syncday.schedulerepeat.command.aggregate.vo.ScheduleDurationVO;

import java.util.List;

public interface ScheduleRepeatDomainService {
    List<ScheduleDurationVO> getRepeatDays(RepeatDTO repeatDTO);
}
