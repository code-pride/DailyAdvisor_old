package com.advisor.model.request;

import java.util.Date;

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
