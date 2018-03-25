package com.advisor.service;

import java.util.*;

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
        Role userRole = roleRepository.findByRole("USER");
        Role userRole2 = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<>(Arrays.asList(userRole, userRole2)));
		userRepository.save(user);

        UserProfile userProfile = new UserProfile(user, newUserRequest);
        userProfileRepository.save(userProfile);
	}

    @Override
    public Optional<User> findById(UUID userId) {
        return userRepository.findById(userId);
    }

	@Override
    public UserProfileResponse createUserProfileResponseByUser(User user){
        UserProfile userProfile = userProfileRepository.findByUser(user);
        return new UserProfileResponse(user, userProfile);
    }

    @Override
    public void updateUserProfile(UserProfileRequest userProfileRequest, UUID userId){
        userProfileRepository.updateUserProfile(userId, userProfileRequest.getCity(), userProfileRequest.getAbout(), userProfileRequest.getName(), userProfileRequest.getLastName());
    }

    @Override
    public void upgradeUserToCoach(User user){

        Role coachRole = roleRepository.findByRole("COACH");
        if(!user.getRoles().contains(coachRole)) {
            user.getRoles().add(coachRole);
            userRepository.save(user);}
        }

    @Override
    public List<UserProfile> findByUsers(List<User> users) {
        Set<User> usersSer = new HashSet<>(users);
        return new ArrayList<>(userProfileRepository.findByUserIn(usersSer));
    }

    @Override
    public List<UserProfile> findByCity(String city) {
        return userProfileRepository.findByCity(city);
    }

}
