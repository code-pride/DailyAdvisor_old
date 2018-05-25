package com.advisor.controllers;

import javax.validation.Valid;

import com.advisor.Events.OnRegistrationCompleteEvent;
import com.advisor.model.entity.User;
import com.advisor.model.request.NewUserRequest;
import com.advisor.model.request.RegistrationConfirmRequest;
import com.advisor.service.Exceptions.DataRepositoryException;
import com.advisor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    final private static String ROLE_CLIENT = "client";
    final private static String ROLE_COACH = "coach";

    @Autowired
    private UserService userService;

    @Autowired
    private SimpleMailMessage mailMessage;

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity createNewUser(@Valid @RequestBody NewUserRequest newUserRequest) {
        if (userService.findUserByEmail(newUserRequest.getEmail()) == null) {
            if (ROLE_CLIENT.equals(newUserRequest.getUserType())) {
                registerUser(ROLE_CLIENT, newUserRequest);
                return new ResponseEntity(HttpStatus.OK);
            } else if (ROLE_COACH.equals(newUserRequest.getUserType())) {
                registerUser(ROLE_COACH, newUserRequest);
                return new ResponseEntity(HttpStatus.OK);
            }
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(HttpStatus.IM_USED);
        }
    }

    @RequestMapping(value = "/registrationConfirm", method = RequestMethod.POST)
    public ResponseEntity confirmRegistration(@RequestBody RegistrationConfirmRequest registrationConfirmRequest) {
        try {
            userService.confirmRegistration(registrationConfirmRequest.getToken());
        } catch (DataRepositoryException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ResponseEntity hello() {
        return new ResponseEntity(HttpStatus.OK);
    }

    private void registerUser(String role, NewUserRequest newUserRequest){
        if(role.equals(ROLE_COACH)){
            userService.registerCoach(newUserRequest);
        } else if (role.equals(ROLE_CLIENT)) {
            userService.registerClient(newUserRequest);
        }

        User user = userService.findUserByEmail(newUserRequest.getEmail());
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user));
    }

}
