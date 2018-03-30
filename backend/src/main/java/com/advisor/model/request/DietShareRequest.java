package com.advisor.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DietShareRequest {

    @NotNull
    private String shareUser;

    @NotNull
    private String dietId;


    public DietShareRequest() {
    }

    public DietShareRequest(String shareUser, String dietId) {
        this.shareUser = shareUser;
        this.dietId = dietId;
    }

}
