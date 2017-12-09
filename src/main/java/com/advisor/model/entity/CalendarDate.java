package com.advisor.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "calendar_date")
public class CalendarDate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "calendar_date_id")
    private int calendarDateId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "calendar_id")
    private Calendar calendarId;
    @Column(name = "date")
    private Integer date;


//TODO gettery settery

}
