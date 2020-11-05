package com.domariev.orderservice.model;

import com.domariev.orderservice.dto.validation.OnCreate;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

@Data
@Entity
@Table(name = "bank_card")
public class BankCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Null(groups = OnCreate.class)
    private Long id;

    @Column(name = "card_number", unique = true)
    @Pattern(regexp = "^\\d{16,18}$")
    private String cardNumber;

    @Column(name = "expiry_date")
    @Pattern(regexp = "^(0[1-9]|1[0-2]|[1-9])/(1[8-9]|[2-9][0-9]|20[1-9][1-9])$")
    private String expiryDate;

    @Column(name = "cvv_code")
    @Max(999)
    private int cvvCode;

}
