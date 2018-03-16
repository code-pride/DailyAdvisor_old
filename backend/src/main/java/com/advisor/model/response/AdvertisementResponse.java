package com.advisor.model.response;

import com.advisor.model.entity.Advertisement;
import lombok.Data;

public @Data
class AdvertisementResponse {
    private Long userId;
    private String name;
    private String lastName;
    private String city;
    private String advertisement;

    public AdvertisementResponse() {
    }

    public AdvertisementResponse(Advertisement advertisement) {
        this.userId = advertisement.getUser().getUserProfile().getUser().getId();
        this.name = advertisement.getUser().getUserProfile().getName();
        this.lastName = advertisement.getUser().getUserProfile().getLastName();
        this.advertisement = advertisement.getAdvText();
        this.city = advertisement.getUser().getUserProfile().getCity();
    }

    public AdvertisementResponse(Long userId, String name, String lastName, String city, String advertisement) {
        this.userId = userId;
        this.name = name;
        this.lastName = lastName;
        this.city = city;
        this.advertisement = advertisement;
    }
}
