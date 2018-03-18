package com.advisor.model.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class UserProfileRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String lastName;

    @NotBlank
    private String city;

    @NotBlank
    private String about;


    public UserProfileRequest() {
    }

    public UserProfileRequest(String name, String lastName, String city, String about) {
        this.name = name;
        this.lastName = lastName;
        this.city = city;
        this.about = about;
    }

}
