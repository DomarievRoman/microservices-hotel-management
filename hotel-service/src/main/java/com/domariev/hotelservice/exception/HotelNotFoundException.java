package com.domariev.hotelservice.exception;

import com.domariev.hotelservice.model.enums.ErrorCode;
import com.domariev.hotelservice.model.enums.ErrorType;

public class HotelNotFoundException extends AbstractException {

    public HotelNotFoundException(String message) {
        super(message);
    }

    public HotelNotFoundException(String message, Throwable cause) {
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
