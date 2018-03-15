package com.advisor.model.request;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Set;

public class TrainListRequest {
    @NotNull
    private Set<TrainingRequest> trainings;

    @NotNull
    private long trainId;

    @NotNull
    private long creatorId;

    @NotBlank
    private String trainName;

    @NotBlank
    private String trainText;


    public TrainListRequest() {
    }

    public Set<TrainingRequest> getTrainings() {
        return trainings;
    }

    public void setTrainings(Set<TrainingRequest> trainings) {
        this.trainings = trainings;
    }

    public long getTrainId() {
        return trainId;
    }

    public void setTrainId(long trainId) {
        this.trainId = trainId;
    }

    public long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(long creatorId) {
        this.creatorId = creatorId;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getTrainText() {
        return trainText;
    }

    public void setTrainText(String trainText) {
        this.trainText = trainText;
    }
}
