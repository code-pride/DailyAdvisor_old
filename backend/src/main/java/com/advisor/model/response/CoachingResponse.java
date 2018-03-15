package com.advisor.model.response;

import com.advisor.model.entity.UserProfile;

public class CoachingResponse {
    private String name;
    private String lastName;
    private String status;

    public CoachingResponse(UserProfile userProfile, String status) {
        this.name = userProfile.getName();
        this.lastName = userProfile.getLastName();
        this.status = status;
    }

    public CoachingResponse(String name, String lastName, String status) {
        this.name = name;
        this.lastName = lastName;
        this.status = status;
    }

    public CoachingResponse() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
