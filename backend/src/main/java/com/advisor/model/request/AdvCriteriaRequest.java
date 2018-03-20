package com.advisor.model.request;

import org.hibernate.validator.constraints.NotBlank;
import lombok.Data;

@Data
public class AdvCriteriaRequest {

    @NotBlank
    private String city;

    @NotBlank
    private String coachType;


    public AdvCriteriaRequest() {
    }

    public AdvCriteriaRequest(String city, String coachType) {
        this.city = city;
        this.coachType = coachType;
    }
}
