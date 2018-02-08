package com.advisor.model.request;

public class AdvCriteriaRequest {
    private String city;
    private String coachType;

    public AdvCriteriaRequest() {
    }

    public AdvCriteriaRequest(String city, String coachType) {
        this.city = city;
        this.coachType = coachType;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCoachType() {
        return coachType;
    }

    public void setCoachType(String coachType) {
        this.coachType = coachType;
    }
}
