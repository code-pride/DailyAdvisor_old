package com.advisor.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, name = "location_id")
    private Long id;

    @NotNull
    @Column(nullable = false, name = "longitude")
    private Double longitude;

    @NotNull
    @Column(nullable = false, name = "latitude")
    private Double latitude;


    public Location() {
    }

    public Location(Double longitude, Double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

}
