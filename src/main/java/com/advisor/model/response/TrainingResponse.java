package com.advisor.model.response;

import com.advisor.model.entity.Training;

public class TrainingResponse {
    private long id;

    private EventResponse event;

    private String trainText;

    public TrainingResponse() {
    }

    public TrainingResponse(Training training) {
        this.id = training.getId();
        this.event = new EventResponse(training.getEvent());
        this.trainText = training.getTrainText();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public EventResponse getEvent() {
        return event;
    }

    public void setEvent(EventResponse event) {
        this.event = event;
    }

    public String getTrainText() {
        return trainText;
    }

    public void setTrainText(String trainText) {
        this.trainText = trainText;
    }
}
