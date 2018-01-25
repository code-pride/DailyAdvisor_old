package com.advisor.model.response;

import com.advisor.model.entity.Location;
import com.advisor.model.entity.Meeting;
import com.advisor.model.entity.User;

public class MeetingResponse {
    private Long meetingId;
    private UserProfileResponse userId;
    private UserProfileResponse userId2;
    private String meetingText;
    private Location location;
    private EventResponse eventResponse;
    private boolean isAccepted;

    public MeetingResponse() {
    }

    public MeetingResponse(Meeting meeting) {
        this.meetingId = meeting.getMeetingId();
        this.userId = new UserProfileResponse(meeting.getUserId().getUserProfile());
        this.userId2 = new UserProfileResponse(meeting.getUserId2().getUserProfile());
        this.meetingText = meeting.getMeetingText();
        this.location = meeting.getLocation();
        this.eventResponse = new EventResponse(meeting.getEvent());
        this.isAccepted = meeting.getAccepted();
    }

    public MeetingResponse(Long meetingId, User userId, User userId2, String meetingText, Location location, EventResponse eventResponse, boolean isAccepted) {
        this.meetingId = meetingId;
        this.userId = new UserProfileResponse(userId.getUserProfile());
        this.userId2 = new UserProfileResponse(userId2.getUserProfile());
        this.meetingText = meetingText;
        this.location = location;
        this.eventResponse = eventResponse;
        this.isAccepted = isAccepted;
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

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public EventResponse getEventResponse() {
        return eventResponse;
    }

    public void setEventResponse(EventResponse eventResponse) {
        this.eventResponse = eventResponse;
    }
}
