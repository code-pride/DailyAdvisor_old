package com.advisor.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;


@Entity
@Data
@Table(name = "user_train")
public class UserTrain {
    @Id
    @org.hibernate.annotations.Type(type = "pg-uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",unique=true, nullable = false)
    private UUID id;

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

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTrain userTrain = (UserTrain) o;
        return Objects.equals(getUser(), userTrain.getUser()) &&
                Objects.equals(getTrain(), userTrain.getTrain()) &&
                Objects.equals(getStatus(), userTrain.getStatus());
    }
}