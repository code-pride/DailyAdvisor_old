package com.advisor.model.request;

import com.advisor.model.entity.Location;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class MeetingRequest {

    private UUID meetingId;

    private UUID userId2;

    @NotBlank
    private String meetingText;

    @NotNull
    private Location location;

    @NotNull
    private EventRequest eventRequest;


    public MeetingRequest() {
    }

    public MeetingRequest(UUID userId2, String meetingText, Location location, EventRequest event) {
        this.userId2 = userId2;
        this.meetingText = meetingText;
        this.location = location;
        this.eventRequest = event;
    }

    public MeetingRequest(UUID userId2, String meetingText, Location location, EventRequest eventRequest, UUID meetingId) {
        this.meetingId = meetingId;
        this.userId2 = userId2;
        this.meetingText = meetingText;
        this.location = location;
        this.eventRequest = eventRequest;
    }

}
