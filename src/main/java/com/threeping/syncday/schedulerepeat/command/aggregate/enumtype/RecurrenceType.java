package com.threeping.syncday.schedulerepeat.command.aggregate.enumtype;

import com.threeping.syncday.common.exception.CommonException;

public enum RecurrenceType {
    EVERYDAY(0),
    EVERY_WEEK_DAY(1),
    EVERY_MONTH_DAY(2),
    EVERY_YEAR_DAY(3),
    ALL_WORK_DAY(4),
    PERSONAL(5);

    private final int value;

    RecurrenceType(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }

    public static RecurrenceType intToRecurrenceType(int value){
        for (RecurrenceType recurrenceType : RecurrenceType.values()){
            if (recurrenceType.getValue() == value){
                return recurrenceType;
            }
        }
        throw new CommonException()
    }
}
public enum Status {
    FIRST(0),
    SECOND(1),
    THIRD(2);

    private final int value;

    // 생성자를 통해 숫자 값을 초기화
    Status(int value) {
        this.value = value;
    }

    // 숫자 값을 반환하는 메서드
    public int getValue() {
        return value;
    }

    // 숫자로 enum을 찾는 정적 메서드
    public static Status fromValue(int value) {
        for (Status status : Status.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid value: " + value);
    }
}
