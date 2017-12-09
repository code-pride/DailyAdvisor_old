package com.advisor.service;

import com.advisor.model.entity.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
}
