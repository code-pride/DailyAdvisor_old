package com.advisor.model.response;

import com.advisor.model.entity.Training;
import lombok.Data;

import java.util.UUID;

@Data
public class TrainingResponse {
    private UUID id;

    private EventResponse event;

    private String trainText;

    public TrainingResponse() {
    }

    public TrainingResponse(Training training) {
        this.id = training.getId();
        this.event = new EventResponse(training.getEvent());
        this.trainText = training.getTrainText();
    }

}
