package com.advisor.model.entity;

import javax.persistence.*;

@Entity
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getCoach() {
        return coach;
    }

    public void setCoach(User coach) {
        this.coach = coach;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
