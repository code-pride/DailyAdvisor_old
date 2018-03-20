package com.advisor.model.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
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

}
