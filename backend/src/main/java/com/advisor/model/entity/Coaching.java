package com.advisor.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name = "coaching")
public class Coaching {
    @Id
    @org.hibernate.annotations.Type(type = "pg-uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",unique=true, nullable = false)
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    private User coach;

    @OneToOne(cascade = CascadeType.ALL)
    private User client;

    @Column(name = "status")
    private String status;


    public Coaching() {
    }

    public Coaching(User coach, User client) {
        this.coach = coach;
        this.client = client;
        this.status = "sent";
    }

}
