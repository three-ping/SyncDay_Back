package com.threeping.syncday.notification.service;

import com.threeping.syncday.notification.infrastructure.InfraNotificationService;
import com.threeping.syncday.scheduleparticipant.query.aggregate.ScheduleParticipantDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class NotificationScheduler {

    private final NotificationService notificationService;
    private final InfraNotificationService infraNotificationService;

    public NotificationScheduler(NotificationService notificationService,
                                 InfraNotificationService infraNotificationService){
        this.notificationService = notificationService;
        this.infraNotificationService = infraNotificationService;
    }

//    @Scheduled(cron = "0 5/10 * * * ?")
    @Scheduled(cron ="0 */1 * * * ?")
    public void scheduleNotificationJob(){
        log.info("일정 알림 scheduling job 실행 시작");
//        infraNotificationService.getParticipantsWithNotificationTimeInNext10Minutes();
//        List<TestUserSchedule> userSchedules = makeDummyData(); // 실제 조회 메소드로 수정 예정
//
//        for (TestUserSchedule testUserSchedule: userSchedules){
//            notificationService.sendScheduleNotification(
//                    testUserSchedule.getUserId(),
//                    testUserSchedule.getScheduleId(),
//                    testUserSchedule.getNotificationTime());
//        }

        List<ScheduleParticipantDTO> schedules = infraNotificationService
                .getParticipantsWithNotificationTimeInNext10Minutes();

        for (ScheduleParticipantDTO scheduleParticipantDTO: schedules){
            notificationService.sendScheduleNotification(
                    scheduleParticipantDTO.getUserId(),
                    scheduleParticipantDTO.getScheduleId(),
                    scheduleParticipantDTO.getNotificationTime());
        }
    }

//    private List<TestUserSchedule> makeDummyData(){
//        ArrayList<TestUserSchedule> result = new ArrayList<>();
//        for(Long i = 0L; i<10; i++){
//            TestUserSchedule testUserSchedule = new TestUserSchedule(
////                    i,i, new Timestamp(System.currentTimeMillis()+120000)
//                    i,i, new Timestamp(System.currentTimeMillis()+10000)
//
//            );
//            result.add(testUserSchedule);
//        }
//        return result;
//    }
}
