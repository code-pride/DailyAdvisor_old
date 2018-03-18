package com.advisor.model.entity;

import com.advisor.model.request.MeetingRequest;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
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

}