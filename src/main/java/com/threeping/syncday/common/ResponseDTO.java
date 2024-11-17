package com.threeping.syncday.common;


import com.fasterxml.jackson.annotation.JsonIgnore;

import com.threeping.syncday.common.exception.CommonException;
import com.threeping.syncday.common.exception.ErrorCode;
import com.threeping.syncday.common.exception.ExceptionDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

//필기. 응답 DTO통일
@Data
public class ResponseDTO<T> {

    @JsonIgnore
    private HttpStatus httpStatus;

    @NotNull
    private boolean success;

    @Nullable
    private T data;

    @Nullable
    private ExceptionDTO error;



    // 기본 생성자
    public ResponseDTO() {
    }

    // 모든 필드를 받는 생성자
    public ResponseDTO(HttpStatus httpStatus, boolean success, @Nullable T data, @Nullable ExceptionDTO error) {
        this.httpStatus = httpStatus;
        this.success = success;
        this.data = data;
        this.error = error;
    }


    // static 팩토리 메소드
    public static <T> ResponseDTO<T> ok(T data) {
        return new ResponseDTO<>(
                HttpStatus.OK,
                true,
                data,
                null
        );
    }


    //필기. 에러 발생시의 메세지(Ad)
    public static ResponseDTO<Object> fail(@NotNull CommonException e) {
        return new ResponseDTO<>(
                e.getErrorCode().getHttpStatus(),
                false,
                null,
                ExceptionDTO.of(e.getErrorCode())
        );
    }

    //필기. 400번 에러 처리(프론트엔드)
    public static ResponseDTO<Object> fail(final MissingServletRequestParameterException e) {
        return new ResponseDTO<>(
                HttpStatus.BAD_REQUEST,
                false,
                null,
                ExceptionDTO.of(ErrorCode.MISSING_REQUEST_PARAMETER)
        );
    }

    public static ResponseDTO<Object> fail(final MethodArgumentTypeMismatchException e) {
        return new ResponseDTO<>(
                HttpStatus.INTERNAL_SERVER_ERROR,
                false,
                null,
                ExceptionDTO.of(ErrorCode.INVALID_PARAMETER_FORMAT)
        );
    }

    public static ResponseDTO<Object> failValidation(MethodArgumentNotValidException e) {
        // 첫 번째 에러 메시지만 사용
        String errorMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();

        // 에러 메시지에 따른 ErrorCode 매핑
        ErrorCode errorCode;
        if (errorMessage.contains("영문자, 숫자, 특수문자")) {
            errorCode = ErrorCode.INVALID_PASSWORD_PATTERN;
        } else if (errorMessage.contains("8자 이상 20자 이하")) {
            errorCode = ErrorCode.INVALID_PASSWORD_LENGTH;
        } else if (errorMessage.contains("3번 이상 연속")) {
            errorCode = ErrorCode.INVALID_PASSWORD_REPEAT;
        } else {
            errorCode = ErrorCode.INVALID_INPUT_VALUE;
        }

        return new ResponseDTO<>(
                HttpStatus.BAD_REQUEST,
                false,
                null,
                ExceptionDTO.of(errorCode)
        );
    }
}