package com.advisor.model.request;

import com.advisor.model.entity.Location;

public class MeetingRequest {
    private Long userId2;
    private String meetingText;
    private Location location;
    private EventRequest eventRequest;

    public MeetingRequest() {
    }

    public MeetingRequest(Long userId2, String meetingText, Location location, EventRequest event) {
        this.userId2 = userId2;
        this.meetingText = meetingText;
        this.location = location;
        this.eventRequest = event;
    }

    public Long getUserId2() {
        return userId2;
    }

    public void setUserId2(Long userId2) {
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

    public EventRequest getEventRequest() {
        return eventRequest;
    }

    public void setEventRequest(EventRequest eventRequest) {
        this.eventRequest = eventRequest;
    }
}
