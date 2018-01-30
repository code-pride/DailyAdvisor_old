package com.advisor.model.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "meal")
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "events")
    private Event events;

    @Column(nullable = false, name = "meal_text")
    private String mealTest;

    @Column(name = "cal")
    private Integer cal;

    @Column(name = "carbohydrates")
    private Float carbohydrates;

    @Column(name = "fat")
    private Integer fat;



//TODO gettery settery

}
