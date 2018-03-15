package com.advisor.model.request;

import org.hibernate.validator.constraints.NotBlank;

public class MealRequest {

    @NotBlank
    private EventRequest event;

    @NotBlank
    private String mealText;

    @NotBlank
    private int cal;

    @NotBlank
    private float carbohydrates;

    @NotBlank
    private int fat;


    public MealRequest() {
    }

    public MealRequest(EventRequest event, String mealText, int cal, float carbohydrates, int fat) {
        this.event = event;
        this.mealText = mealText;
        this.cal = cal;
        this.carbohydrates = carbohydrates;
        this.fat = fat;
    }

    public EventRequest getEvent() {
        return event;
    }

    public void setEvent(EventRequest event) {
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
