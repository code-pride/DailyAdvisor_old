package com.advisor.model.entity;


import com.advisor.model.request.EventRequest;
import com.advisor.model.request.MeetingRequest;

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

    @Column(nullable = false, name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(nullable = false, name = "start_time")
    private Time startTime;

    @Column(nullable = false, name = "end_time")
    private Time endTime;

    @Column(nullable = false, name = "is_full_day_event")
    private boolean isFullDayEvent;

    @Column(nullable = false, name = "is_recurring")
    private boolean isRecurring;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_date", columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
    private Date createDate;

    @Column(name = "parent_event_id")
    private Long parentEventId;

    @OneToMany(cascade= CascadeType.ALL, mappedBy="event_exceptions")
    private Set<EventInstanceException> eventsExceptions;

    public Event() {
    }

    public Event(EventRequest eventRequest) {
        this.startDate = eventRequest.getStartDate();
        this.endDate = eventRequest.getEndDate();
        this.startTime = eventRequest.getStartTime();
        this.endTime = eventRequest.getEndTime();
        this.isFullDayEvent = eventRequest.getFullDayEvent();
        this.isRecurring = eventRequest.getRecurring();
        this.parentEventId = eventRequest.getParentEventId();
        this.eventsExceptions = eventRequest.getEventsExceptions();
    }

    public Event(User userEvent, Date startDate, Date endDate, Time startTime, Time endTime, Boolean isFullDayEvent, Boolean isRecurring, User createdBy, Date createDate, Long parentEventId, Set<EventInstanceException> eventsExceptions) {
        this.userEvent = userEvent;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isFullDayEvent = isFullDayEvent;
        this.isRecurring = isRecurring;
        this.createdBy = createdBy;
        this.createDate = createDate;
        this.parentEventId = parentEventId;
        this.eventsExceptions = eventsExceptions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUserEvent() {
        return userEvent;
    }

    public void setUserEvent(User userEvent) {
        this.userEvent = userEvent;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public Boolean getFullDayEvent() {
        return isFullDayEvent;
    }

    public void setFullDayEvent(Boolean fullDayEvent) {
        isFullDayEvent = fullDayEvent;
    }

    public Boolean getRecurring() {
        return isRecurring;
    }

    public void setRecurring(Boolean recurring) {
        isRecurring = recurring;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getParentEventId() {
        return parentEventId;
    }

    public void setParentEventId(Long parentEventId) {
        this.parentEventId = parentEventId;
    }

    public Set<EventInstanceException> getEventsExceptions() {
        return eventsExceptions;
    }

    public void setEventsExceptions(Set<EventInstanceException> eventsExceptions) {
        this.eventsExceptions = eventsExceptions;
    }
}