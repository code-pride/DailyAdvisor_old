package com.advisor.model.response;

import com.advisor.model.entity.Train;
import com.advisor.model.entity.Training;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
public class TrainResponse {

    private UUID id;

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

}
