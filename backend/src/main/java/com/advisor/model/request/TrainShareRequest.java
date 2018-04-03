package com.advisor.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TrainShareRequest {

    @NotNull
    private String shareUser;

    @NotNull
    private String trainId;


    public TrainShareRequest() {
    }

}
