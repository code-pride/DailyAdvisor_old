package com.advisor.model.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
public class TrainingRequest {

    private String id;


    private EventRequest event;


    private String trainText;


    public TrainingRequest() {
    }

}
