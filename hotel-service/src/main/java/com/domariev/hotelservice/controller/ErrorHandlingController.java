package com.domariev.hotelservice.controller;

import com.domariev.hotelservice.exception.AbstractException;
import com.domariev.hotelservice.model.CustomError;
import com.domariev.hotelservice.model.enums.ErrorCode;
import com.domariev.hotelservice.model.enums.ErrorType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class ErrorHandlingController {

    @ExceptionHandler(AbstractException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CustomError handleAbstractException(AbstractException ex, HandlerMethod handlerMethod) {
        log.error("handleAbstractException() - exception: {}, method {}",
                ex.getMessage(), handlerMethod.getMethod().getName(), ex);
        return new CustomError(ex.getMessage(), ex.getErrorCode(), ex.getErrorType(), LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CustomError handleAllExceptions(Exception ex, HandlerMethod handlerMethod) {
        log.error("handleAllExceptions() - exception: {}, method {}",
                ex.getMessage(), handlerMethod.getMethod().getName(), ex);
        return new CustomError(ex.getMessage(), ErrorCode.APPLICATION_ERROR_CODE, ErrorType.FATAL_ERROR_TYPE, LocalDateTime.now());
    }
}
