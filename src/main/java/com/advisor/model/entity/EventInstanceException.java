package com.advisor.model.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class EventInstanceException {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Event event_exceptions;

    @Column(nullable = false, name = "is_rescheduled")
    private Boolean is_rescheduled;

    @Column(nullable = false, name = "is_cancelled")
    private Boolean is_cancelled;

    @Column(nullable = false, name = "start_date")
    private Date start_date;

    @Column(name = "end_date")
    private Date end_date;

    @Column(nullable = false, name = "start_time")
    private Date start_time;

    @Column(nullable = false, name = "end_time")
    private Date end_time;

    @Column(nullable = false, name = "is_full_day_event")
    private Boolean is_full_day_event;

    @Column(nullable = false, name = "created_by")
    private String created_by;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_date", nullable = false, columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
    private Date createDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventInstanceException that = (EventInstanceException) o;

        if (!id.equals(that.id)) return false;
        if (event_exceptions != null ? !event_exceptions.equals(that.event_exceptions) : that.event_exceptions != null)
            return false;
        if (!is_rescheduled.equals(that.is_rescheduled)) return false;
        if (!is_cancelled.equals(that.is_cancelled)) return false;
        if (!start_date.equals(that.start_date)) return false;
        if (end_date != null ? !end_date.equals(that.end_date) : that.end_date != null) return false;
        if (!start_time.equals(that.start_time)) return false;
        if (!end_time.equals(that.end_time)) return false;
        if (!is_full_day_event.equals(that.is_full_day_event)) return false;
        if (!created_by.equals(that.created_by)) return false;
        return createDate.equals(that.createDate);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (event_exceptions != null ? event_exceptions.hashCode() : 0);
        result = 31 * result + is_rescheduled.hashCode();
        result = 31 * result + is_cancelled.hashCode();
        result = 31 * result + start_date.hashCode();
        result = 31 * result + (end_date != null ? end_date.hashCode() : 0);
        result = 31 * result + start_time.hashCode();
        result = 31 * result + end_time.hashCode();
        result = 31 * result + is_full_day_event.hashCode();
        result = 31 * result + created_by.hashCode();
        result = 31 * result + createDate.hashCode();
        return result;
    }
}
