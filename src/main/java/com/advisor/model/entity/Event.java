package com.advisor.model.entity;


import com.advisor.model.request.EventRequest;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.Set;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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
    private boolean isFullDayEvent;

    @Column(nullable = false, name = "is_recurring")
    private boolean isRecurring;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User createdBy;

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
        this.isFullDayEvent = eventRequest.getFullDayEvent();
        this.isRecurring = eventRequest.getRecurring();
        this.parentEvent = eventRequest.getParentEvent();
        this.eventsExceptions = eventRequest.getEventsExceptions();
        this.recurringPattern = eventRequest.getRecurringPattern();
    }


    public Event(RecurringPattern recurringPattern, Date startDate, Date endDate, Time startTime, Time endTime, boolean isFullDayEvent, boolean isRecurring, User createdBy, Date createDate, Event parentEvent, Set<EventInstanceException> eventsExceptions) {
        this.recurringPattern = recurringPattern;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isFullDayEvent = isFullDayEvent;
        this.isRecurring = isRecurring;
        this.createdBy = createdBy;
        this.createDate = createDate;
        this.parentEvent = parentEvent;
        this.eventsExceptions = eventsExceptions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RecurringPattern getRecurringPattern() {
        return recurringPattern;
    }

    public void setRecurringPattern(RecurringPattern recurringPattern) {
        this.recurringPattern = recurringPattern;
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

    public boolean getFullDayEvent() {
        return isFullDayEvent;
    }

    public void setFullDayEvent(boolean fullDayEvent) {
        isFullDayEvent = fullDayEvent;
    }

    public boolean getRecurring() {
        return isRecurring;
    }

    public void setRecurring(boolean recurring) {
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

    public Event getParentEvent() {
        return parentEvent;
    }

    public void setParentEvent(Event parentEvent) {
        this.parentEvent = parentEvent;
    }

    public Set<EventInstanceException> getEventsExceptions() {
        return eventsExceptions;
    }

    public void setEventsExceptions(Set<EventInstanceException> eventsExceptions) {
        this.eventsExceptions = eventsExceptions;
    }



    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + (getRecurringPattern() != null ? getRecurringPattern().hashCode() : 0);
        result = 31 * result + getStartDate().hashCode();
        result = 31 * result + (getEndDate() != null ? getEndDate().hashCode() : 0);
        result = 31 * result + getStartTime().hashCode();
        result = 31 * result + getEndTime().hashCode();
        result = 31 * result + (getFullDayEvent() ? 1 : 0);
        result = 31 * result + (getRecurring() ? 1 : 0);
        result = 31 * result + getCreatedBy().hashCode();
        result = 31 * result + (getCreateDate() != null ? getCreateDate().hashCode() : 0);
        result = 31 * result + (getParentEvent() != null ? getParentEvent().hashCode() : 0);
        result = 31 * result + (getEventsExceptions() != null ? getEventsExceptions().hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (getFullDayEvent() != event.getFullDayEvent()) return false;
        if (getRecurring() != event.getRecurring()) return false;
        if (getId() != null ? !getId().equals(event.getId()) : event.getId() != null) return false;
        if (getRecurringPattern() != null ? !getRecurringPattern().equals(event.getRecurringPattern()) : event.getRecurringPattern() != null)
            return false;
        if (getStartDate() != null ? !getStartDate().equals(event.getStartDate()) : event.getStartDate() != null)
            return false;
        if (getEndDate() != null ? !getEndDate().equals(event.getEndDate()) : event.getEndDate() != null) return false;
        if (getStartTime() != null ? !getStartTime().equals(event.getStartTime()) : event.getStartTime() != null)
            return false;
        if (getEndTime() != null ? !getEndTime().equals(event.getEndTime()) : event.getEndTime() != null) return false;
        if (getCreatedBy() != null ? !getCreatedBy().equals(event.getCreatedBy()) : event.getCreatedBy() != null)
            return false;
        if (getCreateDate() != null ? !getCreateDate().equals(event.getCreateDate()) : event.getCreateDate() != null)
            return false;
        if (getParentEvent() != null ? !getParentEvent().equals(event.getParentEvent()) : event.getParentEvent() != null)
            return false;
        return getEventsExceptions() != null ? getEventsExceptions().equals(event.getEventsExceptions()) : event.getEventsExceptions() == null;
    }
}