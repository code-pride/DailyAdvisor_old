package com.advisor.model.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
public class TrainingRequest {
    @NotNull
    private String id;

    @NotNull
    private EventRequest event;

    @NotBlank
    private String trainText;


    public TrainingRequest() {
    }

}
