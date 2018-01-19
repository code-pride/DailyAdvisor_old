package com.advisor.model.response;

import com.advisor.model.entity.User;

public class UserResponse {
    public Long id;
    public String name;
    public String lastName;

    public UserResponse() {
    }

    public UserResponse(User user) {
        this.name = user.getName();
        this.lastName = user.getLastName();
        this.id = user.getId();
    }

    public UserResponse(Long id, String name, String lastName) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
