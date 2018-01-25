package com.advisor.model.request;

import com.advisor.model.entity.Event;
import com.advisor.model.entity.EventInstanceException;

import java.sql.Time;
import java.util.Date;
import java.util.Set;

public class EventRequest {
    private Date startDate;
    private Date endDate;
    private Time startTime;
    private Time endTime;
    private boolean isFullDayEvent;
    private boolean isRecurring;
    private Event parentEvent;
    private Set<EventInstanceException> eventsExceptions;

    public EventRequest() {
    }

    public EventRequest(Date startDate, Date endDate, Time startTime, Time endTime, boolean isFullDayEvent, boolean isRecurring, Event parentEvent, Set<EventInstanceException> eventsExceptions) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isFullDayEvent = isFullDayEvent;
        this.isRecurring = isRecurring;
        this.parentEvent = parentEvent;
        this.eventsExceptions = eventsExceptions;
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
}
