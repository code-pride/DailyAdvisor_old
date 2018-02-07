package com.advisor.model.request;


public class CoachingRequest {

    private long coachingId;
    private long coachId;
    private long clientId;


    public CoachingRequest() {
    }

    public CoachingRequest(long coachingId, long coachId, long clientId) {
        this.coachingId = coachingId;
        this.coachId = coachId;
        this.clientId = clientId;
    }

    public long getCoachingId() {
        return coachingId;
    }

    public void setCoachingId(long coachingId) {
        this.coachingId = coachingId;
    }

    public long getCoachId() {
        return coachId;
    }

    public void setCoachId(long coachId) {
        this.coachId = coachId;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }
}
