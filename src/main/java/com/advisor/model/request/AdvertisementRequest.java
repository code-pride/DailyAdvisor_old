package com.advisor.model.request;


import org.hibernate.validator.constraints.NotBlank;

public class AdvertisementRequest {

    @NotBlank
    private String advText;

    @NotBlank
    private String coachType;


    public AdvertisementRequest() {
    }

    public AdvertisementRequest(String advText, String coachType) {
        this.advText = advText;
        this.coachType = coachType;
    }

    public String getAdvText() {
        return advText;
    }

    public void setAdvText(String advText) {
        this.advText = advText;
    }

    public String getCoachType() {
        return coachType;
    }

    public void setCoachType(String coachType) {
        this.coachType = coachType;
    }
}
