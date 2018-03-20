package com.advisor.model.entity;

import com.advisor.model.request.TrainingRequest;
import lombok.Data;

import javax.persistence.*;


@Entity
@Data
@Table(name = "training")
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

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

}