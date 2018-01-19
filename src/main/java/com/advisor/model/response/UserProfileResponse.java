package com.advisor.model.response;

import com.advisor.model.entity.User;
import com.advisor.model.entity.UserProfile;

public class UserProfileResponse {
    private Long userId;
    private String name;
    private String lastName;
    private String city;
    private String about;

    public UserProfileResponse(Long userId, String name, String lastName, String city, String about) {
        this.userId = userId;
        this.name = name;
        this.lastName = lastName;
        this.city = city;
        this.about = about;
    }

    public UserProfileResponse() {
    }

    public UserProfileResponse(User user) {
        this.userId=user.getId();
        this.name=user.getName();
        this.lastName=user.getLastName();
    }

    public UserProfileResponse(User user, UserProfile userProfile) {
        this.userId=user.getId();
        this.name=user.getName();
        this.lastName=user.getLastName();
        this.about=userProfile.getAbout();
        this.city=userProfile.getCity();
    }

    public UserProfileResponse(Long userId) {
        this.userId = userId;
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

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

}
