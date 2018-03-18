package com.advisor.model.request;


import lombok.Data;

@Data
public class CoachingRequest {

    private long coachingId;

    private long coachId;

    private Long clientId;


    public CoachingRequest() {
    }

    public CoachingRequest(long coachingId, long coachId, long clientId) {
        this.coachingId = coachingId;
        this.coachId = coachId;
        this.clientId = clientId;
    }

}
