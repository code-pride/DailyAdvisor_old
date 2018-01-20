package com.advisor.model.entity;

import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.Set;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private User userEvent;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "diet_id")
    private Diet diet;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "meeting_id")
    private Meeting meeting;

    @Column(name = "event_description")
    private String eventDescription;

    @Column(nullable = false, name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(nullable = false, name = "start_time")
    private Time startTime;

    @Column(nullable = false, name = "end_time")
    private Time endTime;

    @Column(nullable = false, name = "is_full_day_event")
    private Boolean isFullDayEvent;

    @Column(nullable = false, name = "is_recurring")
    private Boolean isRecurring;

    @Column(nullable = false, name = "created_by")
    private Long createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_date", nullable = false, columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
    private Date createDate;

    @Column(name = "parent_event_id")
    private Long parentEventId;

    @OneToMany(cascade= CascadeType.ALL, mappedBy="event_exceptions")
    private Set<EventInstanceException> eventsExceptions;
}
