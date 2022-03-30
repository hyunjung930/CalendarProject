package com.hyunjung.finalproject.api.Exception;

import com.hyunjung.finalproject.core.Exception.ErrorCode;
import org.springframework.http.HttpStatus;

public abstract class ErrorHttpStatusMapper {   // abstract: 하위에 static에 생성자를 불러올 수 없게(문법적으로)
    public static HttpStatus mapToStatus(ErrorCode errorCode){
        switch (errorCode){
            case ALREADY_EXIST_USER:
            case VALIDATION_FAIL:
            case BAD_REQUEST:
            case EVENT_CREATE_OVERLAPPED_PERIOD:
                return HttpStatus.BAD_REQUEST;
            case PASSWORD_NOT_MATCH:
            case USER_NOT_FOUND:
                return HttpStatus.UNAUTHORIZED;
            default:
                return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
