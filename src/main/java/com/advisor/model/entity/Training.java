package com.advisor.model.entity;

import com.advisor.model.request.TrainingRequest;

import javax.persistence.*;


@Entity
@Table(name = "training")
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getTrainText() {
        return trainText;
    }

    public void setTrainText(String trainText) {
        this.trainText = trainText;
    }
}