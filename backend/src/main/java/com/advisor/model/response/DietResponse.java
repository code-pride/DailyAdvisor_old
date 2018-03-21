package com.advisor.model.response;

import com.advisor.model.entity.Diet;
import com.advisor.model.entity.Meal;
import lombok.Data;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
public class DietResponse {

    private UUID id;

    private UserProfileResponse createdBy;

    private Set<MealResponse> meals;

    private String status;

    private Date createDate;

    public DietResponse() {
    }

    public DietResponse(Diet diet) {
        this.id = diet.getId();
        this.createdBy = new UserProfileResponse(diet.getCreatedBy().getUserProfile());

        Set<MealResponse> mealResponses = new HashSet<>();
        for (Meal meal : diet.getMeals()) {
            MealResponse mealResponse = new MealResponse(meal);
            mealResponses.add(mealResponse);
        }
        this.meals = mealResponses;
        this.status = diet.getStatus();
        //this.createDate = diet.getCreateDate();
    }

}
