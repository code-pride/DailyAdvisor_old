package com.advisor.model.request;

import javax.validation.constraints.NotNull;

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

    public long getShareUser() {
        return shareUser;
    }

    public void setShareUser(long shareUser) {
        this.shareUser = shareUser;
    }

    public long getDietId() {
        return dietId;
    }

    public void setDietId(long dietId) {
        this.dietId = dietId;
    }
}
