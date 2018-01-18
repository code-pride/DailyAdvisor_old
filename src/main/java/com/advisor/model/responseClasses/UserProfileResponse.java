package com.advisor.model.responseClasses;

import com.advisor.model.entity.User;
import com.advisor.model.entity.UserProfile;

public class UserProfileResponse {
    private Long userId;
    private Long userProfileId;
    private String name;
    private String lastName;
    private String city;
    private String about;

    public UserProfileResponse(Long userId, Long userProfileId, String name, String lastName, String city, String about) {
        this.userId = userId;
        this.userProfileId = userProfileId;
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
        this.userProfileId=userProfile.getUserProfileId();
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

    public Long getUserProfileId() {
        return userProfileId;
    }

    public void setUserProfileId(Long userProfileId) {
        this.userProfileId = userProfileId;
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
