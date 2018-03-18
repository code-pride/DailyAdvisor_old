package com.advisor.model.request;

import com.advisor.model.entity.Location;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
public class MeetingRequest {

    private Long meetingId;

    private Long userId2;

    @NotBlank
    private String meetingText;

    @NotNull
    private Location location;

    @NotNull
    private EventRequest eventRequest;


    public MeetingRequest() {
    }

    public MeetingRequest(Long userId2, String meetingText, Location location, EventRequest event) {
        this.userId2 = userId2;
        this.meetingText = meetingText;
        this.location = location;
        this.eventRequest = event;
    }

    public MeetingRequest(Long userId2, String meetingText, Location location, EventRequest eventRequest, Long meetingId) {
        this.meetingId = meetingId;
        this.userId2 = userId2;
        this.meetingText = meetingText;
        this.location = location;
        this.eventRequest = eventRequest;
    }

}
