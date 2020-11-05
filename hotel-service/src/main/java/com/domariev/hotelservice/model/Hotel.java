package com.domariev.hotelservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "hotels")
@Data
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address", unique = true)
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "stars")
    private int rating;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_id", updatable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Room> rooms;
}
