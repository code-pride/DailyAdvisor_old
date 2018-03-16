package com.advisor.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TrainShareRequest {

    @NotNull
    private long shareUser;

    @NotNull
    private long trainId;


    public TrainShareRequest() {
    }

}
