package com.hyunjung.finalproject.core.Exception;

import lombok.Getter;

@Getter
public class CalendarException extends RuntimeException{

    private final ErrorCode errorCode;

    public CalendarException(ErrorCode errorCode){
        this.errorCode = errorCode;
    }
}
