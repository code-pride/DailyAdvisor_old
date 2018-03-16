package com.advisor.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DietShareRequest {

    @NotNull
    private Long shareUser;

    @NotNull
    private Long dietId;


    public DietShareRequest() {
    }

    public DietShareRequest(long shareUser, long dietId) {
        this.shareUser = shareUser;
        this.dietId = dietId;
    }

}
