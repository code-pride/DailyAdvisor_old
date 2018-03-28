package com.advisor.model.entity;

import com.advisor.model.request.MeetingRequest;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Data
@Table(name = "meeting")
public class Meeting {

    @Id
    @org.hibernate.annotations.Type(type = "pg-uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",unique=true, nullable = false)
    private UUID id;

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

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return Objects.equals(getUserId(), meeting.getUserId()) &&
                Objects.equals(getUserId2(), meeting.getUserId2()) &&
                Objects.equals(getMeetingText(), meeting.getMeetingText()) &&
                Objects.equals(getLocation(), meeting.getLocation()) &&
                Objects.equals(getEvent(), meeting.getEvent()) &&
                Objects.equals(getStatus(), meeting.getStatus());
    }
}