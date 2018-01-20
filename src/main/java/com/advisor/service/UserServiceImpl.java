package com.advisor.service;

import java.util.Arrays;
import java.util.HashSet;

import com.advisor.model.entity.Role;
import com.advisor.model.entity.User;
import com.advisor.model.entity.UserProfile;
import com.advisor.model.request.NewUserRequest;
import com.advisor.model.request.UserProfileRequest;
import com.advisor.model.response.UserProfileResponse;
import com.advisor.repository.RoleRepository;
import com.advisor.repository.UserProfileRepository;
import com.advisor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
    @Qualifier("userRepository")
	private UserRepository userRepository;
    @Autowired
    @Qualifier("userProfileRepository")
    private UserProfileRepository userProfileRepository;
	@Autowired
    @Qualifier("roleRepository")
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

    @Override
	public void saveUser(NewUserRequest newUserRequest) {
	    User user = new User(newUserRequest);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);

        UserProfile userProfile = new UserProfile(user, newUserRequest);
        userProfileRepository.save(userProfile);
	}

    @Override
    public User findUserById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
	public UserProfile findUserProfileByUser(User user){
		return userProfileRepository.findByUser(user);
	}

	@Override
    public UserProfileResponse createUserProfileResponseByUser(User user){
        UserProfile userProfile = userProfileRepository.findByUser(user);

        return new UserProfileResponse(user, userProfile);
    }

    @Override
    public void updateUserProfile(UserProfileRequest userProfileRequest, Long userId){
        userProfileRepository.updateUserProfile(userId, userProfileRequest.getCity(), userProfileRequest.getAbout(), userProfileRequest.getName(), userProfileRequest.getLastName());
    }

    @Override
   public UserProfileResponse createUserResponseByUser(User user){
        return new UserProfileResponse(user.getId());
    }
}
