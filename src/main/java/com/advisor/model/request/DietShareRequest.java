package com.advisor.model.request;

public class DietShareRequest {
    private long shareUser;
    private long dietId;

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
