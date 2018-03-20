package com.advisor.model.entity;

import com.advisor.model.request.NewUserRequest;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user_profile")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_profile_id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    @Column(name = "name")
    private String name;
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "city")
    private String city;

    @Column(name = "about")
    private String about;


    public UserProfile() {
    }

    public UserProfile(User user, NewUserRequest newUserRequest) {
        this.user = user;
        this.name = newUserRequest.getName();
        this.lastName = newUserRequest.getLastName();
        this.city = newUserRequest.getCity();
    }

    public UserProfile(User user) {
        this.user = user;
    }

    public UserProfile(User user, String name, String lastName, String city, String about) {
        this.user = user;
        this.name = name;
        this.lastName = lastName;
        this.city = city;
        this.about = about;
    }

}
