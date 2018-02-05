package com.advisor.model.request;

import java.util.Set;

public class DietListRequest {
    private Set<MealRequest> meals;

    private long dietId;

    private long creatorId;

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

    public long getDietId() {
        return dietId;
    }

    public void setDietId(long dietId) {
        this.dietId = dietId;
    }

    public long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(long creatorId) {
        this.creatorId = creatorId;
    }
}
