package com.advisor.model.request;

import javax.validation.constraints.NotNull;

public class TrainShareRequest {

    @NotNull
    private long shareUser;

    @NotNull
    private long trainId;


    public TrainShareRequest() {
    }

    public long getShareUser() {
        return shareUser;
    }

    public void setShareUser(long shareUser) {
        this.shareUser = shareUser;
    }

    public long getTrainId() {
        return trainId;
    }

    public void setTrainId(long trainId) {
        this.trainId = trainId;
    }
}
