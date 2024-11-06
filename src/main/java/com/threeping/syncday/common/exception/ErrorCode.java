package com.threeping.syncday.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
//필기. 에러 상태별 메시지
@Getter
@AllArgsConstructor
public enum ErrorCode {
    //400
    WRONG_ENTRY_POINT(40000, HttpStatus.BAD_REQUEST, "잘못된 접근입니다"),
    MISSING_REQUEST_PARAMETER(40001, HttpStatus.BAD_REQUEST, "필수 요청 파라미터가 누락되었습니다."),
    INVALID_PARAMETER_FORMAT(40002, HttpStatus.BAD_REQUEST, "요청에 유효하지 않은 인자 형식입니다."),
    BAD_REQUEST_JSON(40003, HttpStatus.BAD_REQUEST, "잘못된 JSON 형식입니다."),
    // 데이터 무결성 위반 추가(ex: db의 NOT NULL 속성)
    DATA_INTEGRITY_VIOLATION(40005, HttpStatus.BAD_REQUEST,
            "데이터 무결성 위반입니다. 필수 값이 누락되었거나 유효하지 않습니다."),
    INVALID_INPUT_VALUE(40010, HttpStatus.BAD_REQUEST, "잘못된 입력 값입니다."),
    INVALID_REQUEST_BODY(40011, HttpStatus.BAD_REQUEST, "잘못된 요청 본문입니다."),
    MISSING_REQUIRED_FIELD(40012, HttpStatus.BAD_REQUEST, "필수 필드가 누락되었습니다."),
    INVALID_VERIFICATION_CODE(40013, HttpStatus.BAD_REQUEST, "잘못된 인증번호입니다. 인증번호를 다시 확인해주세요"),

    // 파일 관련 오류
    UNSUPPORTED_FILE_FORMAT(40020, HttpStatus.BAD_REQUEST, "지원되지 않는 파일 형식입니다."),
    FILE_UPLOAD_ERROR(40021, HttpStatus.BAD_REQUEST, "파일 업로드에 실패했습니다."),
    FILE_CONVERSION_ERROR(40022, HttpStatus.BAD_REQUEST, "파일 변환에 실패했습니다."),
    FILE_SIZE_EXCEEDED(40023, HttpStatus.BAD_REQUEST, "파일 크기가 허용된 최대 크기를 초과했습니다."),

    //401
    INVALID_HEADER_VALUE(40100, HttpStatus.UNAUTHORIZED, "올바르지 않은 헤더값입니다."),

    EXPIRED_TOKEN_ERROR(4010001, HttpStatus.UNAUTHORIZED, "만료된 토큰입니다."),
    INVALID_TOKEN_ERROR(4010002, HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다."),
    TOKEN_MALFORMED_ERROR(4010003, HttpStatus.UNAUTHORIZED, "토큰이 올바르지 않습니다."),
    TOKEN_TYPE_ERROR(4010004, HttpStatus.UNAUTHORIZED, "토큰 타입이 일치하지 않거나 비어있습니다."),
    TOKEN_UNSUPPORTED_ERROR(4010005, HttpStatus.UNAUTHORIZED, "지원하지않는 토큰입니다."),
    TOKEN_GENERATION_ERROR(4010006, HttpStatus.UNAUTHORIZED, "토큰 생성에 실패하였습니다."),
    TOKEN_UNKNOWN_ERROR(4010007, HttpStatus.UNAUTHORIZED, "알 수 없는 토큰입니다."),
    LOGIN_FAILURE(4010008, HttpStatus.UNAUTHORIZED, "로그인에 실패했습니다"),
    UNAUTHORIZED_ACCESS(4010010, HttpStatus.UNAUTHORIZED, "인증되지 않은 접근입니다."),
    EXPIRED_SESSION(4010011, HttpStatus.UNAUTHORIZED, "세션이 만료되었습니다."),
    EXIST_USER_ID(4010012, HttpStatus.UNAUTHORIZED, "이미 회원가입한 회원입니다."),
    DUPLICATE_NICKNAME(4010013, HttpStatus.BAD_REQUEST, "이미 사용 중인 닉네임입니다."),
    DUPLICATE_NICKNAME_EXISTS(4010014, HttpStatus.BAD_REQUEST, "중복된 닉네임입니다."),
    DUPLICATE_EMAIL_EXISTS(4010015,HttpStatus.UNAUTHORIZED,"이미 존재하는 이메일입니다."),
    INVALID_PASSWORD(4010008, HttpStatus.UNAUTHORIZED, "잘못된 비밀번호입니다"),
    //403
    FORBIDDEN_ROLE(4030000, HttpStatus.FORBIDDEN, "권한이 존재하지 않습니다."),
    ACCESS_DENIED(4030010, HttpStatus.FORBIDDEN, "접근이 거부되었습니다."),
    INACTIVE_USER(4030020, HttpStatus.FORBIDDEN, "비활성 회원입니다."),

    //404
    NOT_FOUND_USER(4040001, HttpStatus.NOT_FOUND, "유저가 존재하지 않습니다."),
    EMAIL_VERIFICATION_REQUIRED(4040016, HttpStatus.BAD_REQUEST
            , "이메일 인증이 안된 이메일입니다. 이메일 인증을 완료해주세요."),

    NOT_FOUND_USER_ID(40417, HttpStatus.NOT_FOUND, "아이디를 잘못 입력하셨습니다."),

    //429 (Too Many Requests)
    TOO_MANY_REQUESTS(42900, HttpStatus.TOO_MANY_REQUESTS, "요청 횟수가 너무 많습니다. 잠시 후 다시 시도해 주세요."),

    //500
    INTERNAL_SERVER_ERROR(50000, HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류입니다");


    private final Integer code;
    private final HttpStatus httpStatus;
    private final String message;

}