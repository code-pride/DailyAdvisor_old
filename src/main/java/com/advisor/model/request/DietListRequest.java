package com.advisor.model.request;

import java.util.Set;

public class DietListRequest {
    private Set<MealRequest> meals;

    public DietListRequest() {
    }

    public DietListRequest(Set<MealRequest> meals) {
        this.meals = meals;
    }

    public Set<MealRequest> getMeals() {
        return meals;
    }

    public void setMeals(Set<MealRequest> meals) {
        this.meals = meals;
    }
}
