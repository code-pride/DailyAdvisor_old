package com.advisor.model.response;

import com.advisor.model.entity.Train;
import com.advisor.model.entity.Training;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class TrainResponse {
    private long id;

    private UserProfileResponse createdBy;

    private Set<TrainingResponse> trainings;

    private String status;

    private Date createDate;

    private String trainName;

    private String trainText;

    public TrainResponse() {
    }

    public TrainResponse(Train train) {
        this.id = train.getId();
        this.createdBy = new UserProfileResponse(train.getCreatedBy().getUserProfile());

        Set<TrainingResponse> trainingResponses = new HashSet<>();
        for (Training training : train.getTrainings()) {
            TrainingResponse trainingResponse = new TrainingResponse(training);
            trainingResponses.add(trainingResponse);
        }
        this.trainings = trainingResponses;
        this.status = train.getStatus();
        this.createDate = train.getCreateDate();
        this.trainName = train.getTrainName();
        this.trainText = train.getTrainText();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserProfileResponse getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserProfileResponse createdBy) {
        this.createdBy = createdBy;
    }

    public Set<TrainingResponse> getTrainings() {
        return trainings;
    }

    public void setTrainings(Set<TrainingResponse> trainings) {
        this.trainings = trainings;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
