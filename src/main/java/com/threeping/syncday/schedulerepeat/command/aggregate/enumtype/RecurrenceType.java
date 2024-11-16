package com.threeping.syncday.schedulerepeat.command.aggregate.enumtype;

import lombok.Getter;

@Getter
public enum RecurrenceType {
    EVERYDAY,
    EVERY_WEEK_DAY,
    EVERY_MONTH_DAY,
    EVERY_YEAR_DAY,
    ALL_WORK_DAY,
    PERSONAL
}

