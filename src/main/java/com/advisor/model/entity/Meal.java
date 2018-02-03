package com.advisor.model.entity;

import com.advisor.model.request.MealRequest;

import javax.persistence.*;

@Entity
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getMealText() {
        return mealText;
    }

    public void setMealText(String mealText) {
        this.mealText = mealText;
    }

    public int getCal() {
        return cal;
    }

    public void setCal(int cal) {
        this.cal = cal;
    }

    public float getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(float carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

}
