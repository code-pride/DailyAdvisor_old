package com.advisor.model.entity;

import com.advisor.model.request.MealRequest;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "meal")
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
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
