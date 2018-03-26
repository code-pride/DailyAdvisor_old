package com.advisor.model.entity;

import com.advisor.model.request.MealRequest;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name = "meal")
public class Meal {
    @Id
    @org.hibernate.annotations.Type(type = "pg-uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",unique=true, nullable = false)
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "event")
    private Event event;

    @Column(nullable = false, name = "meal_text")
    private String mealText;

    @Column(name = "cal")
    private int cal;

    @Column(name = "carbohydrates")
    private float carbohydrates;

    @Column(name = "fat")
    private int fat;


    public Meal() {
    }

    public Meal(MealRequest mealRequest) {
        this.event = new Event(mealRequest.getEvent());
        this.mealText = mealRequest.getMealText();
        this.cal = mealRequest.getCal();
        this.carbohydrates = mealRequest.getCarbohydrates();
        this.fat = mealRequest.getFat();
    }

}
