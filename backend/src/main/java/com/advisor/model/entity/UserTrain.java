package com.advisor.model.entity;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
@Table(name = "user_train")
public class UserTrain {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    private Train train;

    @Column(name = "status")
    private String status;


    public UserTrain() {
    }

    public UserTrain(User user, Train train, String status) {
        this.user = user;
        this.train = train;
        this.status = status;
    }

}