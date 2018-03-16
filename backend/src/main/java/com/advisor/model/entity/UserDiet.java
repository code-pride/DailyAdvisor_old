package com.advisor.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
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

}


