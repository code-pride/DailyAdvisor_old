package com.advisor.model.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "coach")
public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "coach_id")
    private long coachId;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_coach", joinColumns = @JoinColumn(name = "coach_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> couches;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_coach", joinColumns = @JoinColumn(name = "coach_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> clients;

    @Column(name = "status")
    private String status;

    public Coach() {
    }

    public Coach(Set<User> couches, Set<User> clients, String status) {
        this.couches = couches;
        this.clients = clients;
        this.status = status;
    }

    public long getCoachId() {
        return coachId;
    }

    public void setCoachId(long coachId) {
        this.coachId = coachId;
    }

    public Set<User> getCouches() {
        return couches;
    }

    public void setCouches(Set<User> couches) {
        this.couches = couches;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<User> getClients() {
        return clients;
    }

    public void setClients(Set<User> clients) {
        this.clients = clients;
    }
}
