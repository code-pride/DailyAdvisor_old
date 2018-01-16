package com.advisor.service;

import com.advisor.model.entity.User;
import com.advisor.model.entity.UserProfile;
import com.advisor.model.responseClasses.UserProfileRequest;
import com.advisor.model.responseClasses.UserProfileResponse;

public interface UserService {
	User findUserByEmail(String email);
	void saveUser(User user);

	User findUserById(Long userId);
	UserProfileResponse createUserProfileResponseByUser(User user);

	void updateUserProfile(UserProfileRequest userProfileRequest, Long userProfileId);
	//UserProfile findUserProfileById(Long id);
}
