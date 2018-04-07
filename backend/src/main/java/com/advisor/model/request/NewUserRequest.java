package com.advisor.model.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class NewUserRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    private String lastName;

    @NotBlank
    private String city;

    @NotBlank
    private String password;

    @NotBlank
    private String userType;


    public NewUserRequest() {
    }

    public NewUserRequest(String email, String name, String lastName, String city, String password, String userType) {
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.city = city;
        this.password = password;
        this.userType = userType;
    }

}
