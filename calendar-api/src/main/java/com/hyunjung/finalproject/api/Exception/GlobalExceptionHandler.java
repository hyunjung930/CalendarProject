package com.hyunjung.finalproject.api.Exception;

import com.hyunjung.finalproject.core.Exception.CalendarException;
import com.hyunjung.finalproject.core.Exception.ErrorCode;
import lombok.Data;
import org.hibernate.cache.CacheException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CacheException.class)
    public ResponseEntity<ErrorResponse> handler(CalendarException ex){
        final ErrorCode errorCode = ex.getErrorCode();
        return new ResponseEntity<>(new ErrorResponse(errorCode, errorCode.getMessage())
                , ErrorHttpStatusMapper.mapToStatus(errorCode));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException ex){    //Validation 실패시 로직
        final ErrorCode errorCode = ErrorCode.VALIDATION_FAIL;
        return new ResponseEntity<>(
                new ErrorResponse(
                        errorCode,
                        Optional.ofNullable(ex.getBindingResult().getFieldError())
                                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                                .orElse(errorCode.getMessage())
                ), ErrorHttpStatusMapper.mapToStatus(errorCode));
    }

    @Data
    public static class ErrorResponse{
        private final ErrorCode errorCode;
        private final String errorMessage;
    }

}
