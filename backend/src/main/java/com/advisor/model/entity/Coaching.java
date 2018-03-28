package com.advisor.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;
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

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User coach;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coaching coaching = (Coaching) o;
        return Objects.equals(getCoach(), coaching.getCoach()) &&
                Objects.equals(getClient(), coaching.getClient()) &&
                Objects.equals(getStatus(), coaching.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
