package com.advisor.model.request;

public class TrainShareRequest {
    private long shareUser;
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
