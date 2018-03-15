package com.advisor.model.response;

import com.advisor.model.entity.Location;
import com.advisor.model.entity.Meeting;

public class MeetingResponse {
    private Long meetingId;
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

    public Long getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Long meetingId) {
        this.meetingId = meetingId;
    }

    public UserProfileResponse getUserId() {
        return userId;
    }

    public void setUserId(UserProfileResponse userId) {
        this.userId = userId;
    }

    public UserProfileResponse getUserId2() {
        return userId2;
    }

    public void setUserId2(UserProfileResponse userId2) {
        this.userId2 = userId2;
    }

    public String getMeetingText() {
        return meetingText;
    }

    public void setMeetingText(String meetingText) {
        this.meetingText = meetingText;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public EventResponse getEventResponse() {
        return eventResponse;
    }

    public void setEventResponse(EventResponse eventResponse) {
        this.eventResponse = eventResponse;
    }
}
