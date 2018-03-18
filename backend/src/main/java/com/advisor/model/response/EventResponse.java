package com.advisor.model.response;

import com.advisor.model.entity.Event;
import com.advisor.model.entity.EventInstanceException;
import lombok.Data;

import java.sql.Time;
import java.util.Date;
import java.util.Set;

@Data
public class EventResponse {
    private Date startDate;
    private Date endDate;
    private Time startTime;
    private Time endTime;
    private boolean fullDayEvent;
    private boolean recurring;
    private Set<EventInstanceException> eventsExceptions;

    public EventResponse(Event event) {
        this.startDate = event.getStartDate();
        this.endDate = event.getEndDate();
        this.startTime = event.getStartTime();
        this.endTime = event.getEndTime();
        this.fullDayEvent = event.getFullDayEvent();
        this.recurring = event.getRecurring();
        this.eventsExceptions = event.getEventsExceptions();
    }

    public EventResponse() {
    }

    public EventResponse(Event event, Event parentEvent) {
        this.startDate = event.getStartDate();
        this.endDate = event.getEndDate();
        this.startTime = event.getStartTime();
        this.endTime = event.getEndTime();
        this.fullDayEvent = event.getFullDayEvent();
        this.recurring = event.getRecurring();
        this.eventsExceptions = event.getEventsExceptions();
    }

    public EventResponse(Date startDate, Date endDate, Time startTime, Time endTime, boolean fullDayEvent, boolean recurring, Set<EventInstanceException> eventsExceptions) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.fullDayEvent = fullDayEvent;
        this.recurring = recurring;
        this.eventsExceptions = eventsExceptions;
    }

}
