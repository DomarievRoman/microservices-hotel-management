package com.domariev.hotelservice.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankCardDto {

    private Long id;

    private String cardNumber;

    private String expiryDate;

    private int cvvCode;
}
