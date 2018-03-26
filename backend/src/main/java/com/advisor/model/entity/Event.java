package com.advisor.model.entity;


import com.advisor.model.request.EventRequest;
import lombok.Data;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
public class Event {
    @Id
    @org.hibernate.annotations.Type(type = "pg-uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",unique=true, nullable = false)
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "recurring_pattern")
    private RecurringPattern recurringPattern;

    @Column(nullable = false, name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(nullable = false, name = "start_time")
    private Time startTime;

    @Column(nullable = false, name = "end_time")
    private Time endTime;

    @Column(nullable = false, name = "is_full_day_event")
    private boolean fullDayEvent;

    @Column(nullable = false, name = "is_recurring")
    private boolean recurring;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_date", columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
    private Date createDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Event parentEvent;

    @OneToMany(cascade= CascadeType.ALL, mappedBy="event_exceptions")
    private Set<EventInstanceException> eventsExceptions;


    public Event() {
    }

    public Event(EventRequest eventRequest) {
        this.startDate = eventRequest.getStartDate();
        this.endDate = eventRequest.getEndDate();
        this.startTime = eventRequest.getStartTime();
        this.endTime = eventRequest.getEndTime();
        this.fullDayEvent = eventRequest.getFullDayEvent();
        this.recurring = eventRequest.getRecurring();
        this.parentEvent = eventRequest.getParentEvent();
        this.eventsExceptions = eventRequest.getEventsExceptions();

        if(eventRequest.getRecurring()){
            this.recurringPattern = eventRequest.getRecurringPattern();
        }
        else {
            this.recurringPattern = null;
        }
    }

    public boolean getFullDayEvent() {
        return fullDayEvent;
    }

    public boolean getRecurring() {
        return recurring;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId());
    }
}