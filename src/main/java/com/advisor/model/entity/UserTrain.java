package com.advisor.model.entity;

import javax.persistence.*;


@Entity
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

}