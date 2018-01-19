package com.advisor.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_profile")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_profile_id")
    private Long userProfileId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    @Column(name = "city")
    private String city;

    @Column(name = "about")
    private String about;
//    @Column(name = "weight")
//    private Float weight;

    public UserProfile() {
    }

    public UserProfile(User user) {
        this.user = user;
    }

    public UserProfile(User user, String city, String about, Float weight) {
        this.user = user;
        this.city = city;
        this.about = about;
//        this.weight = weight;
    }

    public Long getUserProfileId() {
        return userProfileId;
    }

    public void setUserProfileId(Long userProfileId) {
        this.userProfileId = userProfileId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

//    public Float getWeight() {
//        return weight;
//    }
//
//    public void setWeight(Float weight) {
//        this.weight = weight;
//    }

//TODO gettery settery

}
