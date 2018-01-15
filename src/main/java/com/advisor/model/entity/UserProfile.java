package com.advisor.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_profile")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_profile_id")
    private int userProfileId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "city")
    private String city;
    @Column(name = "about")
    private String about;
    @Column(name = "weight")
    private Float weight;

//TODO gettery settery

}
