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
import com.advisor.service.Exceptions.DataRepositoryException;
import com.advisor.service.Exceptions.EntityExists;
import com.advisor.service.Exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    private static final String MESSAGE_NOT_FOUND_MESSAGE_CODE = "exception.entityNotFoundException.meal";

    private static final String MESSAGE_EXISTS_MESSAGE_CODE = "exception.entityNotFoundException.meal";

    private static final String ROLE_COACH = "COACH";

    private static final String ROLE_USER = "USER";

    @Autowired
    @Qualifier("userRepository")
    private UserRepository repository;

    @Autowired
    @Qualifier("userProfileRepository")
    private UserProfileRepository userProfileRepository;

    @Autowired
    @Qualifier("roleRepository")
    private RoleRepository roleRepository;

    @Autowired
    public VerificationTokenService verificationTokenService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User create(User user) throws EntityExists {
        if (user.getId() == null || !repository.existsById(user.getId())) {
            return repository.save(user);
        } else {
            throw new EntityExists(MESSAGE_EXISTS_MESSAGE_CODE);
        }
    }

    @Override
    public void delete(UUID id) throws DataRepositoryException {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException(MESSAGE_NOT_FOUND_MESSAGE_CODE);
        }
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<User> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public User update(User user) throws DataRepositoryException, NoSuchElementException {
        if (repository.existsById(user.getId())) {
            return repository.save(user);
        } else {
            throw new EntityNotFoundException(MESSAGE_NOT_FOUND_MESSAGE_CODE);
        }
    }

    @Override
    public User findUserByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public void registerClient(NewUserRequest newUserRequest) {
        registerUser(newUserRequest, ROLE_USER);
    }

    @Override
    public void registerCoach(NewUserRequest newUserRequest) {
        registerUser(newUserRequest, ROLE_COACH);
    }

    @Override
    public void registerUser(NewUserRequest newUserRequest, String role) {
        User user = new User(newUserRequest);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByRole(role);
        user.setRoles(new HashSet<>(Collections.singletonList(userRole)));
        repository.save(user);

        UserProfile userProfile = new UserProfile(user, newUserRequest);
        userProfileRepository.save(userProfile);
    }

    @Override
    public UserProfileResponse createUserProfileResponseByUser(User user) {
        UserProfile userProfile = userProfileRepository.findByUser(user);
        return new UserProfileResponse(user, userProfile);
    }

    @Override
    public void updateUserProfile(UserProfileRequest userProfileRequest, UUID userId) {
        userProfileRepository.updateUserProfile(userId, userProfileRequest.getCity(), userProfileRequest.getAbout(), userProfileRequest.getName(), userProfileRequest.getLastName());
    }

    @Override
    public void upgradeUserToCoach(User user) {
        Role coachRole = roleRepository.findByRole(ROLE_COACH);
        if (!user.getRoles().contains(coachRole)) {
            user.getRoles().add(coachRole);
            repository.save(user);
        }
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

    @Override
    public User enableUser(User user) throws DataRepositoryException {
        user.setEnabled(true);
        update(user);
        return user;
    }

    @Override
    public boolean confirmRegistration(String token) throws DataRepositoryException {
        User user = verificationTokenService.confirmToken(token);
        enableUser(user);
        return true;
    }

}
