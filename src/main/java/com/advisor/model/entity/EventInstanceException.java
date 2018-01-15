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
    @Column(name = "is_rescheduled")
    private Boolean is_rescheduled;
    @Column(name = "is_cancelled")
    private Boolean is_cancelled;
    @Column(name = "start_date")
    private Date start_date;
    @Column(name = "end_date")
    private Date end_date;
    @Column(name = "start_time")
    private Date start_time;
    @Column(name = "end_time")
    private Date end_time;
    @Column(name = "is_full_day_event")
    private Boolean is_full_day_event;
    @Column(name = "created_by")
    private String created_by;
    @Column(name = "created_date")
    private Date created_date;
}
