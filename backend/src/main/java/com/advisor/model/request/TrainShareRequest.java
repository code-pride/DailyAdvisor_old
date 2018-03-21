package com.advisor.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class TrainShareRequest {

    @NotNull
    private UUID shareUser;

    @NotNull
    private UUID trainId;


    public TrainShareRequest() {
    }

}
