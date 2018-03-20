package com.advisor.model.response;

import com.advisor.model.entity.UserProfile;
import lombok.Data;

@Data
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

}
