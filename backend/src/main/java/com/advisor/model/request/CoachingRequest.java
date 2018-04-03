package com.advisor.model.request;


import lombok.Data;

@Data
public class CoachingRequest {

    private String coachingId;

    private String coachId;

    private String clientId;


    public CoachingRequest() {
    }

    public CoachingRequest(String coachingId, String coachId, String clientId) {
        this.coachingId = coachingId;
        this.coachId = coachId;
        this.clientId = clientId;
    }

}
