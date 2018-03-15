package com.advisor.model.request;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class TrainingRequest {
    @NotNull
    private int id;

    @NotNull
    private EventRequest event;

    @NotBlank
    private String trainText;


    public TrainingRequest() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EventRequest getEvent() {
        return event;
    }

    public void setEvent(EventRequest event) {
        this.event = event;
    }

    public String getTrainText() {
        return trainText;
    }

    public void setTrainText(String trainText) {
        this.trainText = trainText;
    }
}
