package com.advisor.service;

import com.advisor.model.entity.UserEntity;

public interface UserService {
	public UserEntity findUserByEmail(String email);
	public void saveUser(UserEntity user);
}
