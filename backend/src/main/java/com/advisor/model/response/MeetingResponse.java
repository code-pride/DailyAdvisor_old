package com.advisor.model.response;

import com.advisor.model.entity.Location;
import com.advisor.model.entity.Meeting;
import lombok.Data;

import java.util.UUID;

@Data
public class MeetingResponse {
    private UUID meetingId;
    private UserProfileResponse userId;
    private UserProfileResponse userId2;
    private String meetingText;
    private Location location;
    private EventResponse eventResponse;
    private String status;

    public MeetingResponse() {
    }

    public MeetingResponse(Meeting meeting) {
        this.meetingId = meeting.getId();
        this.userId = new UserProfileResponse(meeting.getUserId().getUserProfile());
        this.userId2 = new UserProfileResponse(meeting.getUserId2().getUserProfile());
        this.meetingText = meeting.getMeetingText();
        this.location = meeting.getLocation();
        this.eventResponse = new EventResponse(meeting.getEvent());
        this.status = meeting.getStatus();
    }

}
