package com.advisor.model.response;

import com.advisor.model.entity.Event;
import com.advisor.model.entity.EventInstanceException;

import java.sql.Time;
import java.util.Date;
import java.util.Set;

public class EventResponse {
    private Date startDate;
    private Date endDate;
    private Time startTime;
    private Time endTime;
    private boolean isFullDayEvent;
    private boolean isRecurring;
    private Set<EventInstanceException> eventsExceptions;

    public EventResponse(Event event) {
        this.startDate = event.getStartDate();
        this.endDate = event.getEndDate();
        this.startTime = event.getStartTime();
        this.endTime = event.getEndTime();
        this.isFullDayEvent = event.getFullDayEvent();
        this.isRecurring = event.getRecurring();
        this.eventsExceptions = event.getEventsExceptions();
    }

    public EventResponse() {
    }

    public EventResponse(Event event, Event parentEvent) {
        this.startDate = event.getStartDate();
        this.endDate = event.getEndDate();
        this.startTime = event.getStartTime();
        this.endTime = event.getEndTime();
        this.isFullDayEvent = event.getFullDayEvent();
        this.isRecurring = event.getRecurring();
        this.eventsExceptions = event.getEventsExceptions();
    }

    public EventResponse(Date startDate, Date endDate, Time startTime, Time endTime, boolean isFullDayEvent, boolean isRecurring, Set<EventInstanceException> eventsExceptions) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isFullDayEvent = isFullDayEvent;
        this.isRecurring = isRecurring;
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

    public boolean isFullDayEvent() {
        return isFullDayEvent;
    }

    public void setFullDayEvent(boolean fullDayEvent) {
        isFullDayEvent = fullDayEvent;
    }

    public boolean isRecurring() {
        return isRecurring;
    }

    public void setRecurring(boolean recurring) {
        isRecurring = recurring;
    }

    public Set<EventInstanceException> getEventsExceptions() {
        return eventsExceptions;
    }

    public void setEventsExceptions(Set<EventInstanceException> eventsExceptions) {
        this.eventsExceptions = eventsExceptions;
    }
}