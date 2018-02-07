package com.advisor.model.request;


public class AdvertisementRequest {

    private String advText;

    public AdvertisementRequest() {
    }

    public AdvertisementRequest(String advText) {
        this.advText = advText;
    }

    public String getAdvText() {
        return advText;
    }

    public void setAdvText(String advText) {
        this.advText = advText;
    }
}
