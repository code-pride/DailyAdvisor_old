package com.advisor.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
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


    public EventInstanceException() {
    }

}
