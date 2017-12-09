package com.advisor.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "train_action")
public class TrainAction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "train_action_id")
    private int trainActionId;
    //    @OneToMany(mappedBy = "diet_action")
//    List calendar;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "train_id")
    private Train trainId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "calendar_date_id")
    private CalendarDate calendarDate;



//TODO gettery settery

}