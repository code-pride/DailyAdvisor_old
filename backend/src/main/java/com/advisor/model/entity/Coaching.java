package com.advisor.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "coaching")
public class Coaching {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    private User coach;

    @OneToOne(cascade = CascadeType.ALL)
    private User client;

    @Column(name = "status")
    private String status;


    public Coaching() {
    }

    public Coaching(User coach, User client, String status) {
        this.coach = coach;
        this.client = client;
        this.status = status;
    }

}
