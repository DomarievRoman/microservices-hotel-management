package com.domariev.orderservice.model;

import lombok.Data;

import javax.persistence.*;

@Table(name = "room_order")
@Entity
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_num")
    private String orderNum;

    @OneToOne
    @JoinColumn(name = "card_id")
    private BankCard bankCard;

    @Column(name = "room_id")
    private Long roomId;
}
