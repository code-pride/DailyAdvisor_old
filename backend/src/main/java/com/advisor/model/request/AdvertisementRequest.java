package com.advisor.model.request;


import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class AdvertisementRequest {

    @NotBlank
    private String advText;

    @NotBlank
    private String coachType;


    public AdvertisementRequest() {
    }

    public AdvertisementRequest(String advText, String coachType) {
        this.advText = advText;
        this.coachType = coachType;
    }

}
