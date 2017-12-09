package com.advisor.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "calendar")
public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "calendar_id")
    private int calendarId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User userId;


//TODO gettery settery

}
