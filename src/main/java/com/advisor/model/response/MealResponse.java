package com.advisor.model.response;

import com.advisor.model.entity.Meal;

public class MealResponse {

    private int id;

    private EventResponse event;

    private String mealText;

    private int cal;

    private float carbohydrates;

    private int fat;

    public MealResponse() {
    }

    public MealResponse(Meal meal) {
        this.id = meal.getId();
        this.event = new EventResponse(meal.getEvent());
        this.mealText = meal.getMealText();
        this.cal = meal.getCal();
        this.carbohydrates = meal.getCarbohydrates();
        this.fat = meal.getFat();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EventResponse getEvent() {
        return event;
    }

    public void setEvent(EventResponse event) {
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
