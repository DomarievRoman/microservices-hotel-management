package com.domariev.hotelservice.model;

import com.domariev.hotelservice.model.enums.ErrorCode;
import com.domariev.hotelservice.model.enums.ErrorType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CustomError {
    private String message;
    private ErrorCode errorCode;
    private ErrorType errorType;
    private LocalDateTime dateTime;
}
