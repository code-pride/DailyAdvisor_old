package com.advisor.model.request;


import lombok.Data;

import java.util.UUID;

@Data
public class CoachingRequest {

    private UUID coachingId;

    private UUID coachId;

    private UUID clientId;


    public CoachingRequest() {
    }

    public CoachingRequest(UUID coachingId, UUID coachId, UUID clientId) {
        this.coachingId = coachingId;
        this.coachId = coachId;
        this.clientId = clientId;
    }

}
