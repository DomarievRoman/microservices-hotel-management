package com.domariev.hotelservice.exception;

import com.domariev.hotelservice.model.enums.ErrorCode;
import com.domariev.hotelservice.model.enums.ErrorType;

public class AbstractException extends RuntimeException {

    public AbstractException(String message) {
        super(message);
    }

    public AbstractException(String message, Throwable cause) {
        super(message, cause);
    }

    public ErrorCode getErrorCode() {
        return ErrorCode.APPLICATION_ERROR_CODE;
    }

    public ErrorType getErrorType() {
        return ErrorType.FATAL_ERROR_TYPE;
    }
}
