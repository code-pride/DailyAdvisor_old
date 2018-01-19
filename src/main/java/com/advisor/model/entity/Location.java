package com.advisor.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, name = "location_id")
    private Long locationId;

    @Column(nullable = false, name = "longitude")
    private Double longitude;

    @Column(nullable = false, name = "latitude")
    private Double latitude;

}
