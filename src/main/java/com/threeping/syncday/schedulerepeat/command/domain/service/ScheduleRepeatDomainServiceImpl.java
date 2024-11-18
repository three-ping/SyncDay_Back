package com.threeping.syncday.schedulerepeat.command.domain.service;

import com.threeping.syncday.schedulerepeat.command.aggregate.dto.RepeatDTO;
import com.threeping.syncday.schedulerepeat.command.aggregate.enumtype.RecurrenceType;
import com.threeping.syncday.schedulerepeat.command.aggregate.vo.ScheduleDurationVO;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleRepeatDomainServiceImpl implements ScheduleRepeatDomainService{


    @Override
    public List<ScheduleDurationVO> getRepeatDays(RepeatDTO repeatDTO) {
        long repeatDurationMilliSec = repeatDTO.getRepeatEnd().getTime() - repeatDTO.getStartTime().getTime();
        long scheduleDurationMilliSec = repeatDTO.getEndTime().getTime() - repeatDTO.getStartTime().getTime();
        long oneDayMillis = 24 * 60 * 60 * 1000;
        long oneWeekMillis = oneDayMillis * 7;
        long repeatDurationDays = repeatDurationMilliSec / oneDayMillis;
        long repeatDurationWeeks = repeatDurationDays / oneWeekMillis;
        List<ScheduleDurationVO> repeatDays = new ArrayList<>();

        if (repeatDTO.getRecurrenceType() == RecurrenceType.EVERYDAY){
            for (int i=0; i<repeatDurationDays; i++ ){
                ScheduleDurationVO repeatedDay = new ScheduleDurationVO(
                        new Timestamp(repeatDTO.getStartTime().getTime() + oneDayMillis * i),
                        new Timestamp(repeatDTO.getEndTime().getTime() + oneDayMillis * i)
                );
                repeatDays.add(repeatedDay);
            }
            return repeatDays;
        } else if (repeatDTO.getRecurrenceType() == RecurrenceType.EVERY_WEEK_DAY){
            for (int i=0; i<repeatDurationWeeks; i++ ){
                ScheduleDurationVO repeatedDay = new ScheduleDurationVO(
                        new Timestamp(repeatDTO.getStartTime().getTime() + oneWeekMillis * i),
                        new Timestamp(repeatDTO.getEndTime().getTime() + oneWeekMillis * i)
                );
                repeatDays.add(repeatedDay);
            }
            return repeatDays;
        } else if (repeatDTO.getRecurrenceType() == RecurrenceType.EVERY_MONTH_DAY) {
            // 매달 N일로 구현
            int nowYear = Integer.parseInt(repeatDTO.getStartTime().toString().substring(0,4));
            int nowMonth = Integer.parseInt(repeatDTO.getStartTime().toString().substring(5,7));
            int nowDay = Integer.parseInt(repeatDTO.getStartTime().toString().substring(8,10));
            int endYear = Integer.parseInt(repeatDTO.getRepeatEnd().toString().substring(0,4));
            int endMonth = Integer.parseInt(repeatDTO.getRepeatEnd().toString().substring(5,7));
            String startHM = repeatDTO.getStartTime().toString().substring(11,16);

            while (nowYear < endYear || (nowYear == endYear && nowMonth <= endMonth)) {
                YearMonth yearMonth = YearMonth.of(nowYear, nowMonth);
                int validDay = Math.min(nowDay, yearMonth.lengthOfMonth());

                LocalDateTime startDateTime = LocalDateTime.of(nowYear, nowMonth, validDay,
                        Integer.parseInt(startHM.substring(0, 2)),
                        Integer.parseInt(startHM.substring(3, 5))
                );
                Timestamp startTimeStamp = Timestamp.valueOf(startDateTime);
                Timestamp endTimeStamp = new Timestamp(startTimeStamp.getTime() + scheduleDurationMilliSec);

                repeatDays.add(new ScheduleDurationVO(startTimeStamp, endTimeStamp));

                nowMonth++;
                if (nowMonth > 12) {
                    nowMonth = 1;
                    nowYear++;
                }
            }
            return repeatDays;

        } else if (repeatDTO.getRecurrenceType() == RecurrenceType.EVERY_YEAR_DAY) {
            int nowYear = Integer.parseInt(repeatDTO.getStartTime().toString().substring(0,4));
            int nowMonth = Integer.parseInt(repeatDTO.getStartTime().toString().substring(5,7));
            int nowDay = Integer.parseInt(repeatDTO.getStartTime().toString().substring(8,10));
            int endYear = Integer.parseInt(repeatDTO.getRepeatEnd().toString().substring(0,4));
            String startHM = repeatDTO.getStartTime().toString().substring(11,16);

            for (int year = nowYear; year <= endYear; year++) {
                LocalDateTime startDateTime = LocalDateTime.of(year, nowMonth, nowDay,
                        Integer.parseInt(startHM.substring(0, 2)),
                        Integer.parseInt(startHM.substring(3, 5))
                );
                Timestamp startTimeStamp = Timestamp.valueOf(startDateTime);
                Timestamp endTimeStamp = new Timestamp(startTimeStamp.getTime() + scheduleDurationMilliSec);

                repeatDays.add(new ScheduleDurationVO(startTimeStamp, endTimeStamp));
            }
            return repeatDays;
        } else if (repeatDTO.getRecurrenceType() == RecurrenceType.ALL_WORK_DAY){
            LocalDateTime startDate = repeatDTO.getStartTime().toLocalDateTime();
            LocalDateTime endDate = repeatDTO.getRepeatEnd().toLocalDateTime();

            while (!startDate.isAfter(endDate)) {
                DayOfWeek dayOfWeek = startDate.getDayOfWeek();
                if (dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY) {
                    Timestamp startTimeStamp = Timestamp.valueOf(startDate);
                    Timestamp endTimeStamp = new Timestamp(startTimeStamp.getTime() + scheduleDurationMilliSec);

                    repeatDays.add(new ScheduleDurationVO(startTimeStamp, endTimeStamp));
                }

                startDate = startDate.plusDays(1);
            }
            return repeatDays;
        } else {

        }
        return List.of();
    }
}
