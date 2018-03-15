package com.advisor.model.response;

import com.advisor.model.entity.Diet;
import com.advisor.model.entity.Meal;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class DietResponse {

    private long id;

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
        this.status = status;
        this.createDate = createDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserProfileResponse getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserProfileResponse createdBy) {
        this.createdBy = createdBy;
    }

    public Set<MealResponse> getMeals() {
        return meals;
    }

    public void setMeals(Set<MealResponse> meals) {
        this.meals = meals;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
