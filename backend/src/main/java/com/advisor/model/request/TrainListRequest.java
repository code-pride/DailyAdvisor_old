package com.advisor.model.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class TrainListRequest {

    private Set<TrainingRequest> trainings;


    private String trainId;


    private String creatorId;


    private String trainName;


    private String trainText;


    public TrainListRequest() {
    }

}
