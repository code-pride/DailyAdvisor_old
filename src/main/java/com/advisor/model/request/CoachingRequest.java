package com.advisor.model.request;

import com.advisor.model.entity.User;

public class CoachingRequest {

    private long coachingId;
    private User coach;
    private User client;

    public CoachingRequest(long coachingId, User coach, User client) {
        this.coachingId = coachingId;
        this.coach = coach;
        this.client = client;
    }

    public CoachingRequest() {

    }

    public long getCoachingId() {
        return coachingId;
    }

    public void setCoachingId(long coachingId) {
        this.coachingId = coachingId;
    }

    public User getCoach() {
        return coach;
    }

    public void setCoach(User coach) {
        this.coach = coach;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }
}
