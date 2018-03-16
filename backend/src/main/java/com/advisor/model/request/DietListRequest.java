package com.advisor.model.request;


import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class DietListRequest {

    @NotNull
    private Set<MealRequest> meals;

    private long dietId;

    private long creatorId;


    public DietListRequest() {
    }

}
