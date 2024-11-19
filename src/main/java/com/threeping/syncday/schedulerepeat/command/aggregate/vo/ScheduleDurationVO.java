package com.threeping.syncday.schedulerepeat.command.aggregate.vo;

import com.threeping.syncday.common.exception.CommonException;
import com.threeping.syncday.common.exception.ErrorCode;

import java.sql.Timestamp;

public record ScheduleDurationVO(Timestamp start, Timestamp end) {
    public ScheduleDurationVO {
        validateScheduleTime(start, end);
    }

    private void validateScheduleTime(Timestamp start, Timestamp end) {
        if (start == null || end == null) {
            throw new CommonException(ErrorCode.INVALID_INPUT_VALUE);
        }
        if (start.after(end)) {
            throw new CommonException(ErrorCode.INVALID_REPEAT_SCHEDULE_START_END);
        }
    }
}