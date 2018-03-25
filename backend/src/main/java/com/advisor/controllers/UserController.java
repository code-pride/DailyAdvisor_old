package com.advisor.controllers;

import com.advisor.model.entity.User;
import com.advisor.model.request.UserProfileRequest;
import com.advisor.model.response.UserProfileResponse;
import com.advisor.service.Exceptions.EntityNotFoundException;
import com.advisor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = { "/getUserProfile/{userId}" }, method = RequestMethod.GET)
    public ResponseEntity<UserProfileResponse> getUserProfileByUserId(@PathVariable UUID userId)
    {
        Optional<User> user = userService.findById(userId);
        if(user.isPresent()) {
            UserProfileResponse userProfileResponse = userService.createUserProfileResponseByUser(user.get());
            return new ResponseEntity<>(userProfileResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = { "/updateUserProfile" }, method = RequestMethod.PUT)
    public ResponseEntity updateUserProfile(@Valid @RequestBody UserProfileRequest userProfileRequest)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        userService.updateUserProfile(userProfileRequest, userService.createUserProfileResponseByUser(user).getUserId());

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = { "/upgradeToCoach" }, method = RequestMethod.POST)
    public ResponseEntity upgradeToCoach()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        userService.upgradeUserToCoach(user);

        return new ResponseEntity(HttpStatus.OK);
    }

}
