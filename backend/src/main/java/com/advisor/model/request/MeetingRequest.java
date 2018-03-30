package com.advisor.model.request;

import com.advisor.model.entity.Location;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
public class MeetingRequest {

    private String meetingId;

    private String userId2;

    @NotBlank
    private String meetingText;

    @NotNull
    private Location location;

    @NotNull
    private EventRequest eventRequest;


    public MeetingRequest() {
    }

    public MeetingRequest(String userId2, String meetingText, Location location, EventRequest event) {
        this.userId2 = userId2;
        this.meetingText = meetingText;
        this.location = location;
        this.eventRequest = event;
    }

    public MeetingRequest(String userId2, String meetingText, Location location, EventRequest eventRequest, String meetingId) {
        this.meetingId = meetingId;
        this.userId2 = userId2;
        this.meetingText = meetingText;
        this.location = location;
        this.eventRequest = eventRequest;
    }

}
