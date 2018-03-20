package com.advisor.model.request;

import com.advisor.model.entity.Event;
import com.advisor.model.entity.EventInstanceException;
import com.advisor.model.entity.RecurringPattern;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.util.Date;
import java.util.Set;

@Data
public class EventRequest {

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;

    @NotNull
    private Time startTime;

    @NotNull
    private Time endTime;

    @NotNull
    private boolean fullDayEvent;

    @NotNull
    private boolean recurring;

    @NotNull
    private Event parentEvent;

    @NotNull
    private Set<EventInstanceException> eventsExceptions;

    @NotNull
    private RecurringPattern recurringPattern;


    public EventRequest() {
    }

    public EventRequest(Date startDate, Date endDate, Time startTime, Time endTime, boolean fullDayEvent, boolean recurring, Event parentEvent, Set<EventInstanceException> eventsExceptions, RecurringPattern recurringPattern) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.fullDayEvent = fullDayEvent;
        this.recurring = recurring;
        this.parentEvent = parentEvent;
        this.eventsExceptions = eventsExceptions;
        this.recurringPattern = recurringPattern;
    }

    public boolean getFullDayEvent() {
        return fullDayEvent;
    }

    public boolean getRecurring() {
        return recurring;
    }

}
