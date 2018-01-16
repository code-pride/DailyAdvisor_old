package com.advisor.model.responseClasses;

public class UserProfileRequest {
    private String name;
    private String lastName;
    private String city;
    private String about;

    public UserProfileRequest() {
    }

    public UserProfileRequest(String name, String lastName, String city, String about) {
        this.name = name;
        this.lastName = lastName;
        this.city = city;
        this.about = about;
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
