package com.advisor.model.entity;

import javax.persistence.*;


@Entity
@Table(name = "diet_action")
public class DietAction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "diet_action_id")
    private int dietActionId;
//    @OneToMany(mappedBy = "diet_action")
//    List calendar;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "diet_id")
    private Diet dietId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "calendar_date_id")
    private CalendarDate calendarDate;




//TODO gettery settery

}