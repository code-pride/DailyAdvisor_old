package com.advisor.service;

import java.util.Arrays;
import java.util.HashSet;

import com.advisor.model.entity.RoleEntity;
import com.advisor.model.entity.UserEntity;
import com.advisor.repository.RoleRepository;
import com.advisor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public UserEntity findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void saveUser(UserEntity user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        RoleEntity userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<RoleEntity>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

}
