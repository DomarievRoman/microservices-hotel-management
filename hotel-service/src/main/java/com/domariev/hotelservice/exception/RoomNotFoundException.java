package com.domariev.hotelservice.exception;

import com.domariev.hotelservice.model.enums.ErrorCode;
import com.domariev.hotelservice.model.enums.ErrorType;

public class RoomNotFoundException extends AbstractException {

    public RoomNotFoundException(String message) {
        super(message);
    }

    public RoomNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public ErrorCode getErrorCode() {
        return ErrorCode.DB_ERROR_CODE;
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.PROCESSING_ERROR_TYPE;
    }
}
