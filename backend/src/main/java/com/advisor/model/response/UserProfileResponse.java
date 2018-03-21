package com.advisor.model.response;

import com.advisor.model.entity.User;
import com.advisor.model.entity.UserProfile;
import lombok.Data;

import java.util.UUID;

@Data
public class UserProfileResponse {
    private UUID userId;
    private String name;
    private String lastName;
    private String city;
    private String about;

    public UserProfileResponse(UUID userId, String name, String lastName, String city, String about) {
        this.userId = userId;
        this.name = name;
        this.lastName = lastName;
        this.city = city;
        this.about = about;
    }

    public UserProfileResponse() {
    }

    public UserProfileResponse(User user, UserProfile userProfile) {
        this.userId=user.getId();
        this.name=userProfile.getName();
        this.lastName=userProfile.getLastName();
        this.about=userProfile.getAbout();
        this.city=userProfile.getCity();
    }

    public UserProfileResponse(UserProfile userProfile) {
        this.userId=userProfile.getUser().getId();
        this.name=userProfile.getName();
        this.lastName=userProfile.getLastName();
        this.about=userProfile.getAbout();
        this.city=userProfile.getCity();
    }

}
