package com.advisor.model.response;

import com.advisor.model.entity.Advertisement;

public class AdvertisementResponse {
    private Long userId;
    private String name;
    private String lastName;
    private String city;
    private String advertisement;

    public AdvertisementResponse() {
    }

    public AdvertisementResponse(Advertisement advertisement) {
        this.userId = advertisement.getUser().getId();
        this.name = advertisement.getUser().getName();
        this.lastName = advertisement.getUser().getLastName();
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(String advertisement) {
        this.advertisement = advertisement;
    }
}
