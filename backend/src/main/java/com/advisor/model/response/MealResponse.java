package com.advisor.model.response;

import com.advisor.model.entity.Meal;
import lombok.Data;

@Data
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

}
