package com.advisor.service;

import com.advisor.model.entity.User;
import com.advisor.model.entity.UserProfile;
import com.advisor.model.request.NewUserRequest;
import com.advisor.model.request.UserProfileRequest;
import com.advisor.model.response.UserProfileResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService extends IService<User, UUID> {

	User findUserByEmail(String email);

	void registerClient(NewUserRequest newUserRequest);

	Optional<User> findById(UUID userId);

	void registerCoach(NewUserRequest newUserRequest);

	void registerUser(NewUserRequest newUserRequest, String role);

	UserProfileResponse createUserProfileResponseByUser(User user);

	void updateUserProfile(UserProfileRequest userProfileRequest, UUID userProfileId);

	void upgradeUserToCoach(User user);

    List<UserProfile> findByUsers(List<User> users);

	List<UserProfile> findByCity(String city);
}
