package com.advisor.model.request;

import java.util.Set;

public class TrainListRequest {
    private Set<TrainingRequest> trainings;

    private long trainId;

    private long creatorId;

    private String trainName;

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
