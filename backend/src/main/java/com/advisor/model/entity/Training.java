package com.advisor.model.entity;

import com.advisor.model.request.TrainingRequest;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;


@Entity
@Data
@Table(name = "training")
public class Training {

    @Id
    @org.hibernate.annotations.Type(type = "pg-uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",unique=true, nullable = false)
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event")
    private Event event;

    @Column(nullable = false, name = "training_text")
    private String trainText;

    public Training() {
    }

    public Training(TrainingRequest trainingRequest){
        this.event = new Event(trainingRequest.getEvent());
        this.trainText = trainingRequest.getTrainText();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Training training = (Training) o;
        return Objects.equals(getEvent(), training.getEvent()) &&
                Objects.equals(getTrainText(), training.getTrainText());
    }
}