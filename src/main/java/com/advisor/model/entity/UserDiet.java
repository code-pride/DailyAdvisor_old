package com.advisor.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_diet")
public class UserDiet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    private Diet diet;

    @Column(name = "status")
    private String status;


    public UserDiet() {
    }

    public UserDiet(User user, Diet dietId, String status) {
        this.user = user;
        this.diet = dietId;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Diet getDiet() {
        return diet;
    }

    public void setDiet(Diet diet) {
        this.diet = diet;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}


