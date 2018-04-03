package com.advisor.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Data
public class EventInstanceException {
    @Id
    @org.hibernate.annotations.Type(type = "pg-uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",unique=true, nullable = false)
    private UUID id;

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


    public EventInstanceException() {
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventInstanceException that = (EventInstanceException) o;
        return Objects.equals(getEvent_exceptions(), that.getEvent_exceptions()) &&
                Objects.equals(getIs_rescheduled(), that.getIs_rescheduled()) &&
                Objects.equals(getIs_cancelled(), that.getIs_cancelled()) &&
                Objects.equals(getStart_date(), that.getStart_date()) &&
                Objects.equals(getEnd_date(), that.getEnd_date()) &&
                Objects.equals(getStart_time(), that.getStart_time()) &&
                Objects.equals(getEnd_time(), that.getEnd_time()) &&
                Objects.equals(getIs_full_day_event(), that.getIs_full_day_event()) &&
                Objects.equals(getCreated_by(), that.getCreated_by()) &&
                Objects.equals(getCreateDate(), that.getCreateDate());
    }
}
