package com.advisor.model.entity;

import com.advisor.model.request.MeetingRequest;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "meeting")
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "meeting_id")
    private int meetingId;

    @ManyToOne(cascade = CascadeType.ALL)
    private User userId;

    @ManyToOne(cascade = CascadeType.ALL)
    private User userId2;

    @Column(name = "meeting_text")
    private String meetingText;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Event event;

    private boolean isAccepted;

    public Meeting() {
    }

    public Meeting(User user, User user2, MeetingRequest meetingRequest, Event event) {
        this.userId = user;
        this.userId2 = user2;
        this.meetingText = meetingRequest.getMeetingText();
        this.location = meetingRequest.getLocation();
        this.event = event;
        this.isAccepted = false;
    }

    public Meeting(User userId, User userId2, String meetingText, Location location, Event event, boolean isAccepted) {
        this.userId = userId;
        this.userId2 = userId2;
        this.meetingText = meetingText;
        this.location = location;
        this.event = event;
        this.isAccepted = isAccepted;
    }

    public int getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(int meetingId) {
        this.meetingId = meetingId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public User getUserId2() {
        return userId2;
    }

    public void setUserId2(User userId2) {
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

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

}