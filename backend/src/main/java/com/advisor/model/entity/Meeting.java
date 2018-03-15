package com.advisor.model.entity;

import com.advisor.model.request.MeetingRequest;

import javax.persistence.*;

@Entity
@Table(name = "meeting")
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "meeting_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private User userId;

    @ManyToOne(cascade = CascadeType.ALL)
    private User userId2;

    @Column(name = "meeting_text")
    private String meetingText;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", nullable = false)
    private Event event;

    @Column(name = "status", nullable = false)
    private String status;


    public Meeting() {
    }

    public Meeting(User userId, User userId2, MeetingRequest meetingRequest, Event event) {
        this.userId = userId;
        this.userId2 = userId2;
        this.meetingText = meetingRequest.getMeetingText();
        this.location = meetingRequest.getLocation();
        this.event = event;
        this.status = "sent";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}