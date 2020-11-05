package com.domariev.hotelservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "rooms")
@Data
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "sleeping_places_amount")
    private int sleepingPlacesAmount;

    @Column(name = "floor")
    private int floor;

    @Column(name = "available")
    private boolean available;

    @Column(name = "price_per_day")
    private BigDecimal price;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Hotel hotel;
}
