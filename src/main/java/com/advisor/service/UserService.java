package com.advisor.service;

import com.advisor.model.entity.User;
import com.advisor.model.request.UserProfileRequest;
import com.advisor.model.responseClasses.UserProfileResponse;
import com.advisor.model.responseClasses.UserResponse;
import org.springframework.stereotype.Service;


public interface UserService {
	User findUserByEmail(String email);
	void saveUser(User user);

	User findUserById(Long userId);
	UserProfileResponse createUserProfileResponseByUser(User user);

	void updateUserProfile(UserProfileRequest userProfileRequest, Long userProfileId);

	UserResponse createUserResponseByUser(User user);
	//UserProfile findUserProfileById(Long id);
}
