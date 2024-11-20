package com.threeping.syncday.schedule.query.repository;

import com.threeping.syncday.schedule.query.aggregate.Schedule;
import com.threeping.syncday.schedule.query.aggregate.ScheduleDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ScheduleMapper {
    List<Schedule> selectMySchedulesByUserId(Long userId);

    List<Schedule> selectOthersSchedulesBySearchUserId(Long searchUserId);

    List<Schedule> selectMyDetailSchedulesByUserIdAndScheduleId(@Param("userId") Long userId,
                                                                @Param("scheduleId") Long scheduleId);
}
