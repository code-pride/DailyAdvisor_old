package com.advisor.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class DietShareRequest {

    @NotNull
    private UUID shareUser;

    @NotNull
    private UUID dietId;


    public DietShareRequest() {
    }

    public DietShareRequest(UUID shareUser, UUID dietId) {
        this.shareUser = shareUser;
        this.dietId = dietId;
    }

}
